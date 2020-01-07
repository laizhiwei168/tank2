package com.cyws.tank.utils.common;

public class TankVolumeCalculate {

	/**
	 * 
	 * @param dLiqu_Value 液位
	 * @param Diameter 直径
	 * @param Length 高度
	 * @param Density  密度
	 * @param tankShape  储罐型号
	 * @param maxDvolume  储罐最大容积
	 * @return
	 */
	 public   double CalVolume(double dLiqu_Value,double Diameter,double Length,double Density,String tankShape,Double maxDvolume)
     {  //P=pgh
         double V = 0;
         try
         {
        	 double PI=3.1415926;
             
             double d = Diameter;
             double L = Length;
             //double h = theGsmBoxStatus.fLIQU_VALUE * 101.972 / 1000;   //1千帕=101.972毫米水柱
             //double h = theGsmBoxStatus.fLIQU_VALUE / (9.8 * theGsmBoxPara.fDENSITY);
             //string strLiquidUnit = ConfigurationManager.AppSettings["LiquidUnit"].ToString();
             //if (strLiquidUnit == "CM")
             //{
             //    theGsmBoxStatus.fLIQU_VALUE = (float)h * 100;
             //}
             
             double h = dLiqu_Value/ Density;

            // h = dLiqu_Value * 1000 / (9.8 * Density);

             //if (strLiquidUnit == "CM")
             //{
             //    //h = h * 100;
             //    d = d * 0.01;
             //    L = L * 0.01;
             //}


             if ( "卧式".equals(tankShape))
             {
                 if (h>=0 && h<=d)
                 {
                     h=h;
                 }
                 else if (h>d)
                 {
                     h = d;
                 }
                 V = L * ((d / 2.0) * (d / 2.0) * Math.acos(1 - 2 * h / d) - (Math.sqrt(d * h - h * h)) * (d / 2.0 - h)) + PI * (d / 4.0) * h * h * (1 - 2 * h / (3 * d));

             }
             else if ("立式".equals(tankShape))
             {
                 if (h <= 0)
                 {
                     V = 0;
                 }
                 //case 0<h<=d/4
                 else if (h <= d / 4)
                 {
                     V = PI * h * h * (d - 4 * h / 3);
                 }
                 //case d/4<h<=(L+d/4)
                 else if (h > d / 4 && h <= (L + d / 4))
                 {
                     V = PI * d * d * (h - d / 4) / 4 + PI * d * d * d / 24;
                 }
                 //case h>(L+d/4) && h<=(L+d/2)
                 else if (h > (L + d / 4) && h <= (L + d / 2))
                 {
                     V = PI * (h - L) * (h - L) * (d - 4 * (h - L) / 3) + PI * d * d * L / 4;
                 }
                 //case 超过范围
                 else if (h > (L + d / 2))
                 {
                     h = L + d / 2;
                     V = PI * (h - L) * (h - L) * (d - 4 * (h - L) / 3) + PI * d * d * L / 4;
                 }
                 //case 小于0
                 else if (h <= 0)
                 {
                     V = 0;
                 }
             }
             //是NaN
             if (Double.isNaN(V))
                 V = 0;
             //正无穷大
             if (Double.isInfinite(V))
                 V = 0;
             if (maxDvolume!=null && maxDvolume > 0 && V > maxDvolume)
                 V = maxDvolume;
             return V;
         }
         catch (Exception ex)
         {
             String errMsg = ex + "( CalVOLUME！)";            
             return 0;
         }
     }
}
