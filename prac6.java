
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class prac6 {

    public static void main(String args[]) {
        String input;
        ArrayList<Character> data = new ArrayList<>();
        char[] r = new char[50];
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Data Input:");
        input = sc.next();
        for (int i = 0; i < input.length(); i++) {
            data.add(input.charAt(i));
        }
        
        //0 in p1,p2..
        int i = 0, j = 1;
        int[] p = new int[50];
        int n = 0;
        while (n < data.size()) {
            n = (int) Math.pow(2, i);
            p[j] = n;
            r[n - 1] = '0';
            i++;
            j++;
        }
        
        //rest of data
        i = 0;
        while (!data.isEmpty()) {
            if (r[i] != '0') {
                r[i] = data.remove(0);
            }
            i++;
        }
        
        //to arraylist
        List<Character> roughHamming = new ArrayList<Character>();
        for (char c : r) {
            if (c == '1' || c == '0') {
                roughHamming.add(c);
            }
        }
        roughHamming.add(0, ' ');
        String roughCode = "";
        for(i=0;i<roughHamming.size();i++){
            Character ch = roughHamming.get(i);
            roughCode+=ch;
        }
        System.out.println("Rough hamming code: "+roughCode);
        
        //values for p1,p2...
        for (n = 1; n < j; n++) {
            int k=i=p[n];int cnt=0;
            while (i < roughHamming.size()) {
                if(roughHamming.get(i)=='1')
                    cnt++;
                k--;
                if(k!=0)
                    i++;
                else{
                     i=i+p[n]+1;
                     k=p[n];
                }  
            }
            
            if((cnt%2)!=0){
                roughHamming.set(p[n], '1');
            }
        }
        
        String HammingCode = "";
        for(i=0;i<roughHamming.size();i++){
            Character ch = roughHamming.get(i);
            HammingCode+=ch;
        }
        System.out.println("Rough hamming code: "+HammingCode);
        
        //receiver
        System.out.println("Enter hamming code on receiver:");
        String input1=sc.next();
        
        ArrayList<Character> receiverHamming = new ArrayList<>();
        for (i = 0; i < input1.length(); i++) {
            receiverHamming.add(input1.charAt(i));
        }
        
        //checking p1,p2...
        receiverHamming.add(0, ' ');
        int error=0;
        for (n = 1; n < j; n++) {
            int k=i=p[n];int cnt=0;
            while (i < receiverHamming.size()) {
                if(receiverHamming.get(i)=='1')
                    cnt++;
                k--;
                if(k!=0)
                    i++;
                else{
                     i=i+p[n]+1;
                     k=p[n];
                }  
            }
            
            if((cnt%2)!=0){
                error+=p[n];
            }
        }

        System.out.println("Error: "+error);   
        if(receiverHamming.remove(error)=='1')
            receiverHamming.add(error,'0');
        else
            receiverHamming.add(error,'1');
        
        String recHamming = null;
        for(i=0;i<receiverHamming.size();i++){
            recHamming+=receiverHamming.get(i);
        }
        System.out.println("Corrected hamming code: "+recHamming);
    }
}
