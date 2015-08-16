package auction.utils;

public class RateUtils {


    static public boolean isAvailableRate(int baseAmount, int currentAmount, int checkAmount) {

        final double rateCoeff = 3.0;

        if (checkAmount < 1)
            return false;

        if (checkAmount <= currentAmount)
            return false;

        if (checkAmount < baseAmount / 10) {
            return false;
        }

        if (checkAmount > rateCoeff * currentAmount)
            return false;

        return true;
    }

    static public String getRateAdvs(int baseAmount, int currentAmount) {

    	/*
        int temp=currentAmount;
        int i=0;


        while (temp != 0) {
            temp=temp/10;
            i++;
        }
        */


        int minRate = currentAmount + (currentAmount + baseAmount) / 10;

        return String.valueOf(minRate);
    }
}
