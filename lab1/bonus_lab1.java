class bonus_lab1{
    
    public static void main(String[] args){

        if(args.length!=2){System.out.println("ai nevoie de 2 numere ca argumente"); return;}
        int n= Integer.valueOf(args[0]), k=Integer.valueOf(args[1]);

        // java -Xms4G -Xmx4G hw_lab1 10 3

        if(n>30000){System.out.println("n e mai mare decat 30k"); return; }

        Graph G=new Graph(n,k);
        G.printGraph();
        System.out.print("clica de ");System.out.print(k);System.out.print(" noduri : ");System.out.println(G.testClique(k));
        System.out.println("\n\n\n****\n");
        System.out.print("Clica maxima: ");
        String maxcl=G.maxClique();
        System.out.println(maxcl);
        System.out.println("Pentru orice k > dim(maxClique), nu exista clica de k noduri. Deci pentru orice numar k <= dim(maxClique), exista o clica cu k noduri");

    }
}

class Graph{

    class grade_noduri{

        public int grad;
        public int nod;
        public grade_noduri(int g, int n){grad=g;nod=n;}
    }

    private grade_noduri[] grade_sortate;
    private int[][] matrice;
    private int nr_lines_nr_columns;
    private int k;
    
    public Graph(int n, int nrcl){
        nr_lines_nr_columns=n;
        k=nrcl;
        matrice=new int[n+1][n+1];
        grade_sortate=new grade_noduri[n+1];
        
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
                matrice[v[j]][v[i]]=1;
                //grade_sortate[v[i]]++;
                //grade_sortate[v[j]]++;
            }
        }

        //restul de n-2k noduri
        for(int i=2*k;i<=n;i++){
            int z=(2*k)+(int)(Math.random() * 1000000.0) % (n-2*k);
            matrice[v[i]][z]=1;
            matrice[z][v[i]]=1;
        }

        for(int i=1;i<=n;i++){
            int s=0;
            for(int j=1;j<=n;j++){
                s+=matrice[i][j];
            }
            grade_sortate[i]=new grade_noduri(s,i);
            //grade_sortate[i].nod=i;
            //grade_sortate[i].grad=s;
        }

        boolean b=false;
        while(b==false){
            b=true;
            for(int i=1;i<n;i++){
                if(grade_sortate[i].grad > grade_sortate[i+1].grad){
                    //grade_noduri aux=new grade_noduri(grade_sortate[i].grad, grade_sortate[i].nod);

                    //aux.nod=grade_sortate[i].nod; aux.grad=grade_sortate[i].grad;
                    int aux_grad=grade_sortate[i].grad, aux_nod=grade_sortate[i].nod;
                    grade_sortate[i].nod=grade_sortate[i+1].nod; grade_sortate[i].grad=grade_sortate[i+1].grad;
                    grade_sortate[i+1].nod=aux_nod; grade_sortate[i+1].grad=aux_grad;
                    
                    b=false;
                }
            }
        }
    }

    public void printGraph(){

        System.out.print(" ");for(int i=2;i<=nr_lines_nr_columns;i++){System.out.print("\u2500\u2500");}
        System.out.println("\u2500");
        for(int i=1;i<=nr_lines_nr_columns;i++){
            
            //System.out.print("vecinii nodului ");System.out.print();System.out.print(": ");
            for(int j=1;j<=nr_lines_nr_columns;j++){

                if(j==1){
                    System.out.print("\u2502");
                }
                if(i==j){System.out.print("@");}else System.out.print(matrice[i][j]);
                System.out.print("\u2502");
            }
            System.out.print(": ");
            System.out.println(i);
            for(int j=1;j<=nr_lines_nr_columns;j++){
                System.out.print("\u2500\u2500");
            }
            System.out.println("\u2500");
            
        }
        System.out.print("Gradele nodurilor: ");for(int i=1;i<=nr_lines_nr_columns;i++){System.out.print(grade_sortate[i].grad);System.out.print(" ");}
        System.out.println("");
        System.out.print("Nodurile:          ");for(int i=1;i<=nr_lines_nr_columns;i++){System.out.print(grade_sortate[i].nod);System.out.print(" ");}
        System.out.println("");
    }

    public static void generateCombinations(int[] nums,int[] combination,int start,int pos,int k,int[][] rezComb, int[] row){
        if (pos==k){
            
            //System.arraycopy(combination,0,rezComb[row[0]],0,k);
            for(int j=0;j<k;j++){
                rezComb[row[0]][j]=combination[j];
            }
            row[0]++;
            return;
        }

        for (int i=start;i<nums.length;i++){
            combination[pos]=nums[i];
            generateCombinations(nums,combination,i +1, pos+1,k, rezComb,row);
        }
    }

    public String testClique(int size){

        String rezclique="";

        int pos1=1, pos2=nr_lines_nr_columns;
        for(pos1=1;pos1<=nr_lines_nr_columns;pos1++)if(grade_sortate[pos1].grad>=size-1)break;

        if(pos2-pos1+1<size)return rezclique;
        //System.out.print("pos1: ");System.out.print(pos1);System.out.print("  pos2: ");System.out.println(pos2);

        int combinari_de_n_luate_cate_k=1;
        for (int i=0;i<size;i++){
            combinari_de_n_luate_cate_k=combinari_de_n_luate_cate_k*((pos2-pos1+1) - i) / (i+1);
        }
        
        int[] nums=new int[pos2-pos1+1];
        int[][] rezComb= new int[combinari_de_n_luate_cate_k][size];
        int[] combination =new int[size];
        int[] row={0};
        //Integer row=0;

        for(int i=pos1;i<=pos2;i++){
            nums[i-pos1]=grade_sortate[i].nod;
            //System.out.print(nums[i-pos1]);System.out.print(" ");
        }
        //System.out.println("");
        generateCombinations(nums, combination,0,0,size,rezComb,row);

        /*System.out.println("\nverif rezComb: ");
        for(int i=0;i<combinari_de_n_luate_cate_k;i++){
            for(int j=0;j<size;j++){
                System.out.print(rezComb[i][j]);System.out.print(" ");
            }
            System.out.println("");
        }*/

        boolean isclique=true;
        
        for(int i=0;i<combinari_de_n_luate_cate_k;i++){

            isclique=true;
            for(int j=0;j<size;j++){
                for(int k=j;k<size;k++){
                    if(j!=k){
                        if(matrice[rezComb[i][j]][rezComb[i][k]]==0){isclique=false;break;}
                    }
                    if(isclique==false) break;
                }
            }
            if(isclique==true){
                //System.out.print("Clica: ");
                for(int j=0;j<size;j++){
                    rezclique+=String.valueOf(rezComb[i][j]);rezclique+=" ";
                }
                return rezclique;
            }
            else continue;
        }
        return rezclique;
    }

    public String maxClique(){

        if(this.testClique(nr_lines_nr_columns).length()>0){
            
            return this.testClique(nr_lines_nr_columns);
        }
        
        int nod_st=1, nod_dr=nr_lines_nr_columns;
        int mij=(nod_dr+nod_st)/2;
        int last_nod_dr=nod_dr;
        //System.out.println("verif nod_st : nod_dr");
        //System.out.print(nod_st);System.out.print(" : ");System.out.print(nod_dr);System.out.println("");
        
        while(nod_dr-nod_st !=0){

            mij=(nod_dr+nod_st)/2;
            
                if(this.testClique(nod_dr).length()==0){
                    last_nod_dr=nod_dr;
                    nod_dr=mij;
                    //
                }
                else{
                    
                    nod_st=nod_dr;
                    nod_dr=last_nod_dr;
                }     

            //System.out.print(nod_st);System.out.print(" : ");System.out.print(nod_dr);System.out.println(""); 
        }
        return this.testClique(nod_st);
    }
}


