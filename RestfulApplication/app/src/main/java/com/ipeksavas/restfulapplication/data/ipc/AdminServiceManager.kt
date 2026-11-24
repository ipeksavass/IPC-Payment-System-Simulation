package com.ipeksavas.restfulapplication.data.ipc

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import com.ipeksavas.restfulapplication.utils.IpcProtocol
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withTimeoutOrNull

/*
    * This class is responsible for managing the connection to the AdminService.
    * Since the bindService() and unbindService() methods we'll call to connect to
    AdminService belong to Android's Context class, we need to obtain this object
    from an external source.
 */

class AdminServiceManager(private val context: Context) {
    private val _isConnected = MutableStateFlow(false)//beginning there is no connection to the service
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()//StateFlow listens to the changes in the connection state
    
    private var serviceMessenger: Messenger? = null//beginning there is no connection to the service
    
    private val serviceConnection = object: ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            serviceMessenger = Messenger(service)
            _isConnected.value = true
        }
        
        override fun onServiceDisconnected(name: ComponentName?) {
            serviceMessenger = null
            _isConnected.value = false
            pendingResponse?.cancel()
            pendingResponse = null
        }
    }
    
    fun bind(){
        if(_isConnected.value) return
        
        val intent = Intent().apply{
            component = ComponentName(
                IpcProtocol.ADMIN_SERVICE_PACKAGE,
                IpcProtocol.ADMIN_SERVICE_CLASS
            )
        }
        context.applicationContext.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        
    }
    
    fun unbind(){
        if(_isConnected.value){
            context.applicationContext.unbindService(serviceConnection)
            serviceMessenger = null
            _isConnected.value = false
            pendingResponse?.cancel()
            pendingResponse = null
        }
    }
    
    private var pendingResponse: CompletableDeferred<Bundle>? = null
    private val incomingHandler = object: Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            if(msg.what == IpcProtocol.MSG_PAYMENT_RESPONSE){
                pendingResponse?.complete(Bundle(msg.data))
            }else{
                super.handleMessage(msg)
            }
        }
    }
    private val replyMessenger = Messenger(incomingHandler)
    
    suspend fun sendRequest(what:Int, payload:Bundle): Result<Bundle>{
        val messenger = serviceMessenger ?: return Result.failure(IllegalStateException("Service is not connected"))
        val deferred = CompletableDeferred<Bundle>()
        pendingResponse = deferred
        return try{
            val msg = Message.obtain(null, what)
            msg.data = payload
            msg.replyTo = replyMessenger
            messenger.send(msg)
            val response = withTimeoutOrNull(10_000L){deferred.await()}
            if(response != null) Result.success(response)
            else Result.failure(Exception("Timeout waiting for response"))
        }
        catch(e: Exception) { Result.failure(e)}
        finally{ pendingResponse = null }
    }
}