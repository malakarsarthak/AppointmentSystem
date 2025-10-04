package com.it.utilis;

import java.util.Random;

public class UtilsFuncs 
{
   public static int generatePWD()
   {
	   Random random = new Random();
	   int number = 1000 + random.nextInt(9000);
	   return number;
   }
}
