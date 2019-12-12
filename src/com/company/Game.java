package com.company;

import java.util.Dictionary;
import java.util.Scanner;

public class Game {
    private Dictionary<String, Situation> occasions = new Dictionary<String, Situation>();
    private Player pt;
    private Scanner scan = new Scanner(System.in);
    private boolean isPlaying;
    
    public Game(){
        pt = new Player("Przemyslaw", 100);
        isPlaying = true;
        
        Situation.Answer[] tempAns = {new Situation.Answer("Да", "start", 0, ""),
                new Situation.Answer("Нет", "exit", 0, "")};
        Situation temp = new Situation("Вы убиты, начать заново?","dead", tempAns);
        try {
            occasions.put("dead", temp.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        
        tempAns = new Situation.Answer[] {new Situation.Answer("Да, конечно! Вперёд к приключениям!", "inCave", 0, "Мне нравится ваш энтузиазм!\n"),
                new Situation.Answer("Ну даже не знаю... Ладно", "inCave", -1, "Вас наказали боги за нерешительность.\n"),};
        temp = new Situation("Вы видите вход в пещеру. Возможно, там спрятано древнее сокровище. Пойдём внутрь?","start", tempAns);
        try {
            occasions.put("start", temp.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        
        tempAns = new Situation.Answer[] {new Situation.Answer("Попробуем напасть на него!", "dead", -100, "Что ж, нападать на тролля без оружия было не самой лучшей идеей.\n"),
                new Situation.Answer("Подкрадусь незаметно и заберу сокровище...", "success", 0, "Большая удача! Вы забрали кучу золота из сундука и улизнули незамеченным!\n"),};
        temp = new Situation("Вы заходите в пещеру и погружаетесь во мрак, через некоторое время, вы видите заветный сундук, но его охраняет огромный тролль. Что будем делать?","inCave", tempAns);
        try {
            occasions.put("inCave", temp.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        tempAns = new Situation.Answer[] {new Situation.Answer("Конечно!", "start", 0, ""),
                new Situation.Answer("Неее", "exit", 0, ""),};
        temp = new Situation("Идём дальше? Может нарвёмся на другие приключения...","success", tempAns);
        try {
            occasions.put("success", temp.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        
    }
    
    public void start(){
        String currentSituationId = "start";
        while (isPlaying){
            currentSituationId = dialog(currentSituationId);
            if(currentSituationId.equals("exit")) {
                isPlaying = false;
                System.out.println("Что ж, до встречи.");
            }
        }
        
    }

    private String dialog(String id){
        Situation current = occasions.get(id);
        System.out.println(current.getCondition());
        System.out.println("-------------");
        for (int i = 0; i < current.answers.length; i++) {
            System.out.println((int)(i+1) + ": " + current.answers[i].getText());
        }
        System.out.println("-------------");
        System.out.print("Ваш ответ: ");
        int ans = scan.nextInt();

        System.out.print(current.answers[ans - 1].getWhyDamaged());
        System.out.println("=====================");
        return current.answers[ans - 1].getNextSituationId();
    }
}
