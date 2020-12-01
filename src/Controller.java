package src;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

public class Controller {

    FIS fis;
    FuzzyRuleSet fuzzyRuleSet;

    public Controller(){
        try {
            fis = FIS.load("./controller.fcl",false);
            fuzzyRuleSet = fis.getFuzzyRuleSet();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public double getAngle(Distances distances){
        double angle = 0;
        try {
            this.fuzzyRuleSet.setVariable("left", distances.left);
            this.fuzzyRuleSet.setVariable("front", distances.front);
            this.fuzzyRuleSet.setVariable("right", distances.right);
            this.fuzzyRuleSet.evaluate();
            angle = fuzzyRuleSet.getVariable("angle").defuzzify();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return angle;
    }

    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();

        controller.fuzzyRuleSet.chart();
        try {
            controller.fuzzyRuleSet.setVariable("left", 70);
            controller.fuzzyRuleSet.setVariable("front", 70);
            controller.fuzzyRuleSet.setVariable("right", 200);
            controller.fuzzyRuleSet.evaluate();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        System.out.println(controller.fuzzyRuleSet.getVariable("angle").defuzzify());
        controller.fuzzyRuleSet.getVariable("angle").chartDefuzzifier(true);
    }

}