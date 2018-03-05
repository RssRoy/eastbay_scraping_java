package practice;

/*class MyThread extends Thread{
	MyThread(){}
	MyThread(Runnable r){super(r);}
	public void run() {
		System.out.println("Hello Thread");
	}
}
class MyRunnable implements Runnable{
	public void run() {
		System.out.println("Hello runnable");
	}
}*/
class B{
	public static void main(String args[]) {
		/*new MyThread().start();
		new MyThread(new MyRunnable()).start();*/
		int i=4,j=3,k=0;
		k = ++i - --j+ i++ - --j+j++;
		System.out.println(i+" "+j+" "+k);
	}
}
