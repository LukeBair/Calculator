package com.company;

/*
sorry for the lack of comments. I've been trying ideas for like 6 hours, so I'm kind of tired.
This is just kind of an idea with some sample code. I also can't do interface, I just use the console.
There is a sample function down below to show how this type of data structure would be beneficial.

*/
public class Ent {
    String data = "";
    Ent child; //the child's index is distinct from its parent's
    Ent parent;
    Ent previous;
    Ent next;
    int index = 0;
    int depth = 0;


    public void setNext() {
        next = new Ent();
        next.data = "";
        next.previous = this;
        next.index = this.index+1;
        next.parent = this;
        next.depth = this.depth;
    }

    public void setChild(){
        child = new Ent();
        child.data = "";
        child.index = 0;
        child.depth = this.depth+1;
        child.parent = this;
    }

    Ent goToIndex(int i) { //returns the Ent at the i-th index. It's not strictly neccessary for this project, but I thought it would be logical to have since this is based on lists.
        Ent auxiliary = this;
        while (i != auxiliary.index){
            if (i > auxiliary.index){
                auxiliary = this.next;
            }
            else{
                auxiliary = this.previous;
            }
        }
        return auxiliary;
    }

    public static void displayEnt(String string) { //try calling this function on this string: "8*(20+2)1-(3+(5-(1+1-)2*(3)/4))2+3". For now, it just looks like a fancy partitioning method based on parentheses.
        Ent auxiliary = new Ent();
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if (character == '('){
                System.out.println("Depth: + " + auxiliary.depth + " Index: " + auxiliary.index + " Data: " + auxiliary.data); //The idea is to evaluate the expressions displayed here.
                auxiliary.setNext();
                auxiliary = auxiliary.next;
                auxiliary.setChild();
                auxiliary = auxiliary.child;
            }
            else if (character == ')') {
                System.out.println("Depth: + " + auxiliary.depth + " Index: " + auxiliary.index + " Data: " + auxiliary.data); //The idea is to evaluate the expressions displayed here.
                auxiliary = auxiliary.parent;
                auxiliary.setNext();
                auxiliary = auxiliary.next;
            }
            else {
                auxiliary.data += character;
            }
        }
        System.out.println("Depth: + " + auxiliary.depth + " Index: " + auxiliary.index + " Data: " + auxiliary.data);
    }
}
