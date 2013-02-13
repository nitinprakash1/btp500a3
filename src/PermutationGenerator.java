public class PermutationGenerator
{
    // private data

    private int[] perm;
    private boolean first;

    // constructor

    public PermutationGenerator (int n)
    {
        perm = new int [n];
        first = true;
    }


    // return the next permutation, or null if no more
    // Reference: Wikipedia Permutation 5.2.2

    public int[] next ()
    {
        int n = perm.length;

        // starting permutation: 0 1 2 3 ... n-1

        if (first)
        {
            first = false;
            for (int i = 0 ; i < n ; i++)
                perm [i] = i;
            return perm;
        }

        // construct the next permutation
        // find largest k so that perm[k] < perm[k+1]; if none, finish

        int i, j, k, l;

        for (k = n - 2 ; k >= 0 && perm [k] >= perm [k + 1] ; k--)
            ;
        if (k < 0)
            return null; // no more

        // find largest l so that perm[k] < perm[l]

        for (l = n - 1 ; l >= 0 && perm [k] >= perm [l] ; l--)
            ;

        // swap perm[k] and perm[l]

        swap (perm, k, l);

        // reverse perm[k+1]...perm[n-1]

        for (i = k + 1, j = n - 1 ; i < j ; i++, j--)
            swap (perm, i, j);

        return perm;
    }


    // swap a[i] and a[j]

    private  void swap (int a[], int i, int j)
    {
        int temp = a [i];
        a [i] = a [j];
        a [j] = temp;
    }
}
