package Yuklemeler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import OyunAlani.SkorEkran;

public class Skorlar {
	public static ArrayList<OyuncuSkor> liste;
    public static final int MAX_LISTE=5;
	public Skorlar()
	{
		liste=new ArrayList<OyuncuSkor>();
		
	}

    public static void oku() {

    	try {
       
            String inputFileName  = "Skorlar.txt";
            

            FileReader inputFileReader   = new FileReader(inputFileName);
            
            BufferedReader inputStream   = new BufferedReader(inputFileReader);
            

           

            String inLine = null;

            while ((inLine = inputStream.readLine()) != null) {
                String[] gecici=new String[2];
			    int i=0;
			    StringTokenizer st = new StringTokenizer(inLine);
			    while (st.hasMoreTokens()) {
			        gecici[i]=st.nextToken();
			        i++;
			    }
			    i=0;
			    int skor=Integer.parseInt(gecici[0]);
			    String ad=gecici[1];
			
			    OyuncuSkor gy=new OyuncuSkor(skor,ad);
			    liste.add(gy);
            }

            
           
            inputStream.close();

        } catch (IOException e) {

            System.out.println("IOException:");
            e.printStackTrace();

        }

    }

    public static void yaz() {

    	try {

    		String outputFileName = "Skorlar.txt";

    		FileWriter outputFileReader  = new FileWriter(outputFileName);
    		PrintWriter    outputStream  = new PrintWriter(outputFileReader);
    	    for(int i=0;i<MAX_LISTE;i++)
    	    {
    	    	
    	    	outputStream.println(liste.get(i).skor+" "+liste.get(i).ad);
    	    }
    		              
    		 outputStream.close();

        } catch (IOException e) {

            System.out.println("IOException:");
            e.printStackTrace();

        }

    }
    

      
 public void Listele()
	{ 
	if(liste.isEmpty()==false)
	 {
		SkorEkran se=new SkorEkran();
		se.setVisible(true);
		int sayi;
		if(liste.size()>=MAX_LISTE)
			sayi=MAX_LISTE;
		else
			{
			   sayi=liste.size();
			   for(int i=0;i<5-sayi;i++)
				{
					OyuncuSkor o=new OyuncuSkor(0,"Catali");
					liste.add(o);
				}
			}
			
		se.jL12.setText(liste.get(0).ad);
		se.jL13.setText(Integer.toString(liste.get(0).skor));
		
		se.jL22.setText(liste.get(1).ad);
		se.jL23.setText(Integer.toString(liste.get(1).skor));
		
		se.jL32.setText(liste.get(2).ad);
		se.jL33.setText(Integer.toString(liste.get(2).skor));
		
		se.jL42.setText(liste.get(3).ad);
		se.jL43.setText(Integer.toString(liste.get(3).skor));
		
		se.jL52.setText(liste.get(4).ad);
		se.j53.setText(Integer.toString(liste.get(4).skor));
	 }
		
		
	}
 
 public void SkorEkleme(OyuncuSkor o)	{
		for(int i=0;i<MAX_LISTE;i++)
		{
			if(o.skor>=liste.get(i).skor)
			 
			{
				liste.add(i, o);
		    	liste.remove(MAX_LISTE);
                break;			
			}
		}
		}
		
	

}
