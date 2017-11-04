import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class PascalTriangle extends	RecursiveTask<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private	static	Map<String,	PascalTriangle>	instances	= new	HashMap<String,	PascalTriangle>();	
	
	public	static	PascalTriangle	getInstance(long n,	long k)		
	{	
		String	key	=	n	+	",	"	+	k;	
		if	(!instances.containsKey(key))
		{
			instances.put(key,	new	PascalTriangle(n,k));	
		}	
		return	instances.get(key);	
	}	
	
	private final long n;
	private final long k;
	
	private  PascalTriangle(long _n, long _k) {
		 this.n=_n; 
		 this.k=_k;
	}
	
	
	protected Long compute() {
		if (n == 0 || k == 0 || n == k) {
		return (long) 1;
		}
		PascalTriangle left = new PascalTriangle(n - 1, k - 1);
		PascalTriangle right = new  PascalTriangle(n - 1, k);
		left.fork();	
		return	right.compute()	+	left.join();	
		}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long startTime = System.currentTimeMillis();
		
		ForkJoinPool	pool	=	new	ForkJoinPool(3);	
		PascalTriangle	task	=	new	PascalTriangle(80,6);	
		
		long result	=	pool.invoke(task);	
		
		System.out.println(result);
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime/1000 + "s");
	}

	//27s 17s 14s

}
