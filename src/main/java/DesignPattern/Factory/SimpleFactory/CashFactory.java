package DesignPattern.Factory.SimpleFactory;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-02
 * Time: 19:45
 */
public class CashFactory {
    public static CashSuper createCashAccept(String type) {
        CashSuper res;
        switch (type) {
            case "return":
                res = new CashReturn(300, 100);
                break;
            case "sale":
                res = new CashRebate(0.8);
                break;
            default:
                res = new NormalCash();
        }

        return res;
    }

    public static void main(String[] args) {
        CashSuper cs = CashFactory.createCashAccept("sale");
        double totalPrices = 0d;
        totalPrices += cs.acceptCash(10);
        System.out.println(totalPrices);
    }
}


// base class for price calculating
abstract class CashSuper {
    abstract double acceptCash(double money);
}

class NormalCash extends CashSuper{
    @Override
    double acceptCash(double money) {
        return money;
    }
}

// sale
class CashRebate extends CashSuper {
    private double saleRate = 1.0;
    public CashRebate(double moneyRebate) {
        saleRate = moneyRebate;
    }
    @Override
    double acceptCash(double money) {
        return money * saleRate;
    }
}

// return
class CashReturn extends CashSuper {
    private double moneyCondition = 0;
    private double moneyReturn = 0;
    public CashReturn(int moneyCondition, int moneyReturn) {
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }
    @Override
    double acceptCash(double money) {
        return money - Math.floor(money / moneyCondition) * moneyReturn;
    }
}
