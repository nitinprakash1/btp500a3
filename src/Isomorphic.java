import java.util.Arrays;

class Isomorphic
{
	
	public boolean [][]Graph1;
	public boolean [][]Graph2; 
	public boolean [][]Graph3;

	public static int l;
	public String regex = "-|\\s+";
	public int []G1;
	public int []G2;
	public int []G3;
	
	protected static int []p;
	public static String G[] = new String[2];
	
	public int ARRAYSIZE;
	public PermutationGenerator Permutation;
	
	
	public static void main(String []args)
	{
		Isomorphic t = new Isomorphic("0-4 0-5 0-6 1-4 1-5 1-7 2-4 2-6 2-7 3-5 3-6 3-7","0-1 1-2 2-3 3-0 4-5 5-6 6-7 7-4 0-4 1-5 2-6 3-7");
		String Graph[] = G;
		System.out.println("Brute Force Isomorphism checker");
		System.out.println("Graph 1: "+ Graph[0]); System.out.println("Graph 2: " + Graph[1]);
		if(t.PermutateGraph())
		{
			System.out.println("RESULT: The two graphs show Isomorphism!");
			String temp = "";
			for(int i=0;i<l;i++) 
			{
				temp = temp + p[i];
				if(i<l-1)
				{
					temp = temp + " ";
				}
			}
			System.out.println("PERMUTATION: " + temp);
			
		}
		else
		{
			System.out.println("RESULT: The two graphs DO NOT seem to be Isomorphic!");
		}
	}
	
	/*
	 * The constructor accepts the two test case G in the string format provided then converts the
	 * string test case into an array of integers. The constructor then generates a 2d boolean array which
	 * is filled with values using the values of the test G as index values. Thus the edges are determined
	 * and where ever the vertices meet the position is made TRUE.
	 * This method also keeps comparing graph1 and graph3 to see if they are identical, using the java method
	 * deepEquals. The comparison takes place as long as there exists a next permutation and there haven't
	 * been any successful permutations yet.
	 */
	public Isomorphic(String Graph_One,String Graph_Two)
	{
		ARRAYSIZE = 0;
		int half = ARRAYSIZE/2;
		extractgraph(Graph_One,Graph_Two);
		Graph1 = new boolean[(ARRAYSIZE/2)-1][(ARRAYSIZE/2)-1];
		Graph2 = new boolean[(ARRAYSIZE/2)-1][(ARRAYSIZE/2)-1];
		
		for(int i=0;i<ARRAYSIZE;i+=2)
		{
			Graph1[G1[i]][G1[i+1]] = true;
			Graph1[G1[i+1]][G1[i]] = true;
			Graph2[G2[i]][G2[i+1]] = true;
			Graph2[G2[i+1]][G2[i]] = true;
		}

		int m=0;
		for(int k=0;k<G1.length;k++)
		{
			if(G1[k]>m)
			{
				m = G1[k];
			}
		}
		l = m +1;
		Permutation = new PermutationGenerator(l);
		p = new int[l];
		G[0] = Graph_One;
		G[1] = Graph_Two;
		Graph3 = new boolean[(ARRAYSIZE/2)-1][(ARRAYSIZE/2)-1];
	}
	
	/*
	 * Regular expression is used to extract the graph values from the formatted string provided 
	 * as test case graph. Then integer arrays G1 and G2 are populated with the integer values of the
	 * graph after the values have been parsed into integers.
	 */
	public void extractgraph(String Graph1 , String Graph2)
	{
		int c1=0,c2=0;
		String[] Graph1Parts = Graph1.split(regex);
		String[] Graph2Parts = Graph2.split(regex);		
		ARRAYSIZE = Graph1Parts.length ;	
		initializer();		
		for(int i=0;i<ARRAYSIZE;i++)
		{
			G1[i] = Integer.parseInt(Graph1Parts[i]);
			G2[i] = Integer.parseInt(Graph2Parts[i]);
			c1++;c2++;
		}
	}

	public int returnarray(int i)
	{
		return p[i];
	}
	
	/*
	 * Next permutation is received from the PermutationGenerator class, if there exists further permutations.
	 * If there is another permutation for g2 test case then it reloads the array G3 with values based on 
	 * mapping using the permutation. Then boolean graph3 is initialized with false values. The values in the 
	 * boolean array graph 3 are changed using the permutation as index values.
	 * If there are no more permutations left, then false is returned thus making the test Graphs non isomorphic.
	 */
	public boolean PermutateGraph()
	{
		int temp = 0;
		p = Permutation.next();
		//System.out.println();
		boolean flag1 = true;
		boolean flag2 = false;
		if(p != null)
		{
			for(int i=0;i<ARRAYSIZE;i++ )
			{
				
				for(int k=0;k<l;k++)
				{
					if(p[k] == G2[i])
					{
					//	System.out.println(p[k]);
						temp = k;
					}
				}
				G3[i] = temp;
		}
			for(int i=0;i<l;i++)
			{
				for(int j=0;j<l;j++)
				{
					Graph3[i][j] = false;
				}
			}
			for(int i=0;i<ARRAYSIZE;i+=2)
			{
				Graph3[G3[i]][G3[i+1]] = true;
				Graph3[G3[i+1]][G3[i]] = true;
			}
		}
		else
		{
			flag1 = false;
		}
		
		do
		{
			if(Arrays.deepEquals(Graph1, Graph3))
			{
				flag2 = true;
			}
		}while(!flag1 && flag2);
		
		return flag1;
	}
	
	/*
	 * Initialize arrays.
	 */
	public void initializer()
	{
		G1 = new int[ARRAYSIZE];
		G2 = new int[ARRAYSIZE];
		G3 = new int[ARRAYSIZE];
	}
	
}


