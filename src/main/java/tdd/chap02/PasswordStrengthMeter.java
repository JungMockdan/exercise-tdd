package main.java.tdd.chap02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        int metCounts = getMetCriteriaCounts(s);

        if(metCounts<=1) return PasswordStrength.WEAK;
        if(metCounts==2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(String s) {
        int metCounts = 0;
        boolean lengthEnough = s.length()>=8;
        if(lengthEnough) metCounts++;
        if(isContainsNum(s)) metCounts++;
        if(isContainsUppercase(s)) metCounts++;
        return metCounts;
    }

    private boolean isContainsNum(String s) {
        boolean containsCriteria=false;
        String regx = ".*[0-9]+.*";//숫자포함여부
        containsCriteria = s.matches(regx);
        return containsCriteria;
    }

    private boolean isContainsUppercase(String s) {
        boolean containsNum=false;
        String regx = ".*[A-Z]+.*";//대문자포함여부
        containsNum = s.matches(regx);
        return containsNum;
    }
}
