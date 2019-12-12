package com.company;

public class Situation implements Cloneable{
    private String condition;
    private String id;
    public final Answer[] answers;
    public Situation(String condition, String id, Answer[] answers){
        this.condition = condition;
        this.id = id;
        this.answers = answers.clone();
    }

    public String getCondition(){
        return condition;
    }

    @Override
    protected Situation clone() throws CloneNotSupportedException {
        Answer[] temp = new Answer[answers.length];
        for(int i = 0; i < answers.length; i++){
            temp[i] = (Answer) answers[i].clone();
        }
        Situation clone = new Situation(this.condition, this.id, temp);
        return clone;
    }

    static class Answer implements Cloneable{
        private String text;
        private String nextSituationId;
        private int healthDamage;
        private String whyDamaged;

        public Answer(String text, String nextSituationId, int healthDamage, String whyDamaged){
            this.nextSituationId = nextSituationId;
            this.text = text;
            this.healthDamage = healthDamage;
            this.whyDamaged = whyDamaged;
        }

        public String getText() {
            return text;
        }

        public String getNextSituationId() {
            return nextSituationId;
        }

        public int getHealthDamage() {
            return healthDamage;
        }

        public String getWhyDamaged() {
            return whyDamaged;
        }

        @Override
        protected Answer clone() throws CloneNotSupportedException {
            Answer clone = new Answer(this.text, this.nextSituationId, this.healthDamage, this.whyDamaged);
            return clone;
        }
    }
}
