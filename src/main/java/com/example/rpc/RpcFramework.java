package com.example.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 自定义RPC框架
 * @author OKali
 *
 */
public class RpcFramework {

	/**
	 * 监听端口，把接口对象暴露出去，等待消费者请求
	 * @param service 接口对象实例
	 * @param interfaceClazz  暴露的接口
	 * @param port    端口
	 * @throws Exception
	 */
	public static void export(final Object service, final Class interfaceClazz, int port) throws Exception {
		if (service == null) {
			throw new IllegalAccessException("service instance  == null");
		}
		if (port < 0 || port > 65535) {
			throw new IllegalAccessException("Invalid port" + port);
		}
		System.out.println("Export service " + service.getClass().getName() + " on port: " + port);
		ServerSocket serverSocket = new ServerSocket(port);
		// 循环等待，每来一个请求开启一个线程处理
		for(;;) {
			// 等待请求
			final Socket socket = serverSocket.accept();
			try {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							
							try {
								// 获取客户端发送过来的信息
								ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
								String interfaceName = input.readUTF();
								String methodName = input.readUTF();
								Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
								Object[] arguments = (Object[]) input.readObject();  
								
								ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
								try {
									if (!interfaceName.equals(interfaceClazz.getName())) {
										throw new IllegalAccessException("Interface wrong,export:" + interfaceClazz 
												+ " refer:" + interfaceName);
									}
									// 将客户端发送过来的信息，反射调用，将对象发挥给客户端
									Method method  = service.getClass().getMethod(methodName, parameterTypes);
									Object result = method.invoke(service, arguments); // 把对象返回给消费者，客户端
									output.writeObject(result);
								} catch (Throwable e) {
									e.printStackTrace();
								} finally {
									output.close();
									input.close();
								}
								
							} catch (Throwable e) {
								e.printStackTrace();
							}
						} catch (Throwable e) {
							e.printStackTrace();
						} finally {
							try {
								socket.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						
					}
				}).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 消费者请求一个远程接口调用，创建一个代理类
	 * @param interfaceClass  接口
	 * @param host			  地址
	 * @param port			 端口
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws Exception {  
        if (interfaceClass == null) {  
            throw new IllegalAccessException("Interface class == null");  
        }  
        if (!interfaceClass.isInterface()) {  
            throw new IllegalAccessException(interfaceClass.getName() + " must be interface");  
        }  
        if (host == null || host.length() == 0) {  
            throw new IllegalAccessException("host == null");  
        }  
        if (port <= 0 || port > 65535) {  
            throw new IllegalAccessException("Invalid port " + port);  
        }  
        System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);  
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] { interfaceClass },  
                new InvocationHandler() {  // 返回一个代理类
  
        			// 调用代理方法，返回值
                    @Override  
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {  
                    	
                        Socket socket = new Socket(host, port);  // 通过Socket把请求的信息发送给服务器端
                        try {  
                        	// 通过Socket把请求的信息发送给服务器端
                            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());  
  
                            try {  
                                output.writeUTF(interfaceClass.getName());  
                                output.writeUTF(method.getName());  
                                output.writeObject(method.getParameterTypes());  
                                output.writeObject(args);  
  
                                // 服务器端拿到发送过去的接口类以及端口对象，服务器端监听端口获得信息，通过反射把对象发送回来
                                // 客户端拿到返回的对象
                                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());  
  
                                try {  
                                    Object result = input.readObject();  
                                    if (result instanceof Throwable) {  
                                        throw (Throwable) result;  
                                    }  
                                    return result;  
                                } finally {  
                                    input.close();  
                                }  
  
                            } finally {  
                                output.close();  
                            }  
  
                        } finally {  
                            socket.close();  
                        }  
                    }  
                });  
    }  
  
}
