public class x{
	public static void main(String[] args) {
		x asd= new x();
		String xa = "C++ is !my faveorie, lan.gausef{dssd}adfs [sadf-sdf]+dfss=";
		String[] arr = xa.split("\\s+|\\{|}|\\[|]|=|<|>|\\(|\\)|\\.|,|;|'|\"|\\?|#|!|-|:");
		// { } [ ] < > = ( ) . , ; ’ ” ? # ! - :
		// System.out.print(xa.toLowerCase());	
		for(int i=0;i<arr.length;i++)
		{
			System.out.println(arr[i]);
		}
	}
}