package com.example.concurrency.st;

/**
 * 安全停止线程
 * @author OKali
 *
 */
public class StopThreadSafe {
	
	public static User u = new User();
	
	public static void main(String[] args) throws InterruptedException {
		new ReadObjectThread().start();
		while (true) {
			Thread t = new ChangeObjectThread();
			t.start();
			Thread.sleep(150);
			((ChangeObjectThread) t).stopMe();
		}
	}
	
	public static class ChangeObjectThread extends Thread {
		
		volatile boolean stop = false;  // 用于多线程停止
		
		public void stopMe() {
			stop = true;
		}
		
		@Override
		public void run() {
			while (true) {
				if (stop) {
					System.out.println("exit by stop me.");
					break;
				}
				synchronized (u) {
					int v = (int) (System.currentTimeMillis() / 1000);
					// dosomething
					u.setId(v);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					u.setName(String.valueOf(v));
				}
				Thread.yield();
			}
		}
	}
	
	public static class ReadObjectThread extends Thread {
		
		@Override
		public void run() {
			while(true) {
				synchronized (u) {
					if (u.getId() != Integer.parseInt(u.getName())) {
						System.out.println(u.toString());
					} else {
						System.out.println(" the same");
					}
				}
				Thread.yield();
			}
		}
	}

	public static class User {
		private int id;
		private String name;
		
		public User() {
			id = 0;
			name = "0";
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + "]";
		}
		
		
	}
}
