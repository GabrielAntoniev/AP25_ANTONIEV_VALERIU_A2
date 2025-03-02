public class lab1{

    public static void main(String[] args){

        lab1 l1= new lab1();
        System.out.println("Hello ba\n***");
        String[] languages={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n=n*3;
        n+=0b10101+0xFF;
        n*=6;
        int s=0;
   

        if(n==0)s=0;
        else
        if(n%9==0)s=9;
        else s=n%9;
        //if (s==9)s--;

        System.out.println("Willy-nilly, this semester I will learn " + languages[s]);


            /*0 1 .. 9 +9+9+9+9/./.
            
            7845 -> 24 -> 6
            7845-9-9-9-9-9-9-9-9

            x + 10 -> +1
            14+9

            2+9+9+9*/

    
    }
}
