class hw_lab1{

    public static void main(String[] args){
        
        if(args.length!=2){System.out.println("ai nevoie de 2 numere ca argumente"); return;}
        int n= Integer.valueOf(args[0]), k=Integer.valueOf(args[1]);

        
        if(n<=30000)
       {Graph G=new Graph(n,k);
        G.printGraph();
        System.out.print("nr muchii=");System.out.println(G.nrEdges());
        System.out.print("Δ(G)=");System.out.println(G.DELTA());
        System.out.print("δ(G)=");System.out.println(G.delta());
        System.out.print("este suma gradelor=2m? ");System.out.println(G.check_2m());
        System.out.println("\n");System.out.println(G.getMatrixintoString());
        } // java -Xms4G -Xmx4G hw_lab1 10 3

        else{System.out.println("n e mai mare decat 30k"); return; }

    }
}

class Graph{

    private int[][] matrice;
    private int nr_lines_nr_columns;
    private int k;

    public Graph(int n, int nrcl){
        nr_lines_nr_columns=n;
        k=nrcl;
        matrice=new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            for(int j=i;j<=n;j++){
                matrice[i][j]=0;
            }
        }

        //clica random
        int[]v=new int[n+1]; for(int i=1;i<=n;i++)v[i]=i;
        for(int i=1;i<=n/2;i++){
            int a1=1+(int)(Math.random() * 1000000.0) % n;
            int a2=1+(int)(Math.random() * 1000000.0) % n;
            int aux=v[a1];v[a1]=v[a2];v[a2]=aux;
        }

        for(int i=1;i<=n;i++){System.out.print(v[i]); System.out.print(" ");}System.out.println("");//asta ii permutarea random pt clica

        for(int i=1;i<=k;i++){
            for(int j=i;j<=k;j++){
            if(v[i]==v[j]){continue;}
            matrice[v[i]][v[j]]=1;
            matrice[v[j]][v[i]]=1;}
        }

        //restul de n-2k noduri
        for(int i=2*k;i<=n;i++){
            int z=(2*k)+(int)(Math.random() * 1000000.0) % (n-2*k);
            matrice[v[i]][z]=1;
            matrice[z][v[i]]=1;
        }
/*
        for(int i=1;i<=n;i++){
            for(int j=i;j<=n;j++){
                
                
                if(i==j){matrice[i][i]=0;continue;}
                matrice[i][j]=(int)(Math.random() * 1000000.0) % (2);
                matrice[j][i]=matrice[i][j];
            }
        }*/
    }


    public void printGraph(){

        System.out.print(" ");for(int i=2;i<=nr_lines_nr_columns;i++){System.out.print("\u2500\u2500");}
        System.out.println("\u2500");
        for(int i=1;i<=nr_lines_nr_columns;i++){
            
            for(int j=1;j<=nr_lines_nr_columns;j++){

                if(j==1){
                    System.out.print("\u2502");
                }
                //System.out.print("\u2502");
                if(i==j){System.out.print("@");}else System.out.print(matrice[i][j]);
                //System.out.print(" ");
                System.out.print("\u2502");
            }
            System.out.println("");
            for(int j=1;j<=nr_lines_nr_columns;j++){
                System.out.print("\u2500\u2500");
            }
            System.out.println("\u2500");
        }
        //System.out.print(" ");//for(int i=2;i<=nr_lines_nr_columns;i++){System.out.print("\u2500\u2500");}
        //System.out.println("\u2500");
    }

    public String getMatrixintoString(){

        String rez=new String();
        rez+= " ";
        for(int i=2;i<= nr_lines_nr_columns;i++){
            rez+="\u2500\u2500";
        }
        rez+="\u2500\n";
        for(int i=1;i<=nr_lines_nr_columns;i++){
            
            for(int j=1;j<=nr_lines_nr_columns;j++){

                if(j==1){
                    rez+="\u2502";
                }
                //System.out.print("\u2502");
                if(i==j){rez+="@";}else{
                    
                    if(matrice[i][j]==0)rez+="0";else{rez+="1";}
                };
                //System.out.print(" ");
                rez+="\u2502";
            }
            rez+="\n";
            for(int j=1;j<=nr_lines_nr_columns;j++){
                rez+="\u2500\u2500";
            }
            rez+="\u2500\n";
        }
        return rez;
    }

    public int nrEdges(){
        int m=0;
        for(int i=1;i<=nr_lines_nr_columns;i++){
            for(int j=i;j<=nr_lines_nr_columns;j++){
                if(i==j){continue;}
                if(matrice[i][j]==1)m++;
            }
        }
        return m;
    }

    public int DELTA(){
        int max=0;
        for(int i =1 ;i<nr_lines_nr_columns;i++){
            int s=0;
            for(int j=1;j<nr_lines_nr_columns;j++){
                if(matrice[i][j]==1)s++;
            }
            if(s>max)max=s;
        }
        return max;
    }

    public int delta(){
        int min=999;
        for(int i =1 ;i<nr_lines_nr_columns;i++){
            int s=0;
            for(int j=1;j<nr_lines_nr_columns;j++){
                if(matrice[i][j]==1)s++;
            }
            if(s<min)min=s;
        }
        return min; 
    }

    public String check_2m(){

        int s=0;
        for(int i =1 ;i<nr_lines_nr_columns;i++){
            
            for(int j=1;j<nr_lines_nr_columns;j++){
                if(matrice[i][j]==1)s++;
            }
        }

        if(s==2*this.nrEdges())return "TRUE";return "FALSE";
    }
}