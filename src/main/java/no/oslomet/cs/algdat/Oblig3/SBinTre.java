package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {

        //kopierer kildekode fra Kompendie Programkode 5.2.3 a)

        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        //legger til en ny node legger q som parrent.
        p = new Node<>(verdi,q);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        return true;
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public boolean fjern(T verdi) {

        if(inneholder(verdi)){
            if (verdi == null) return false;  // treet har ingen nullverdier

            Node<T> p = rot, foreldre = null;   // q skal være forelder til p

            while (p != null)            // leter etter verdi
            {
                int cmp = comp.compare(verdi,p.verdi);      // sammenligner
                if (cmp < 0) { foreldre = p; p = p.venstre; }      // går til venstre
                else if (cmp > 0) { foreldre = p; p = p.høyre; }   // går til høyre
                else break;    // den søkte verdien ligger i p
            }
            if (p == null) return false;   // finner ikke verdi

            if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
            {
                Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
                if (p == rot) rot = b;
                else if (p == foreldre.venstre) foreldre.venstre = b;
                else {
                    foreldre.høyre = b;
                    if(b !=null){
                        b.forelder=foreldre;// min kode
                    }
                }
            }
            else  // Tilfelle 3)
            {
                Node<T> s = p, r = p.høyre;   // finner neste i inorden
                while (r.venstre != null)
                {
                    s = r;    // s er forelder til r
                    r = r.venstre;
                }

                p.verdi = r.verdi;   // kopierer verdien i r til p

                if (s != p) s.venstre = r.høyre;
                else s.høyre = r.høyre;
            }

            antall--;   // det er nå én node mindre i treet
            return true;
        }

        else return false;
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        int finnes = 0; // lager en ny variabel for hver gang verdien finnes
        if (verdi == null) return 0;

        Node<T> p = rot;

        while (p != null) {
            if(verdi == p.verdi) finnes ++; // når verdien blir funnet legges det til på finnes
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp >= 0) p = p.høyre; //sørger for at pekeren flyttes slik at vi kan fortsette å søke. Har brukt eksempel fra Andre sin forelesningsvideo AlgDat2020 Uke 10 binære søketrær og binærsøk
        }

        return finnes;
        // throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {

        //kopiert kildekode fra kompendiet Programkode 5.1.7 h)
        while (true)
        {
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            else return p;
        }

        //throw new UnsupportedOperationException("Ikke kodet ennå!");

    }

    private static <T> Node<T> nestePostorden(Node<T> p) {

        /*

        I kompendiet står det regler for hvordan man finner den neste.
        Postorden:

Hvis p ikke har en forelder ( p er rotnoden), så er p den siste i postorden.
Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
Hvis p er venstre barn til sin forelder f, gjelder:
Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
Hvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.
         */

        Node<T> foreldre = p.forelder;

        if(foreldre == null){
            return null;
        }

        if(p == foreldre.høyre){
            return foreldre;
        }

        if(p == foreldre.venstre){
            if(foreldre.høyre == null){
                return foreldre;
            }
        }
        return førstePostorden(foreldre.høyre);

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postorden(Oppgave<? super T> oppgave) {

        Node<T> p = førstePostorden(rot); //tilordner første postorden
        oppgave.utførOppgave(p.verdi); //skriver ut
        while(true){ //går i while løkke helt til neste postorden node er null.
            p = nestePostorden(p);
            if(p == null)break;
            oppgave.utførOppgave(p.verdi);

        }




        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        if(p == null){ //basistilfelle
            return;
        }

        //bruker kildekode fra forelesning i Algdat.
        postordenRecursive(p.venstre,oppgave);
        postordenRecursive(p.høyre,oppgave);
        oppgave.utførOppgave(p.verdi);

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public static void main(String[] args) {
        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
        SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) { tre.leggInn(verdi); }
        System.out.println(tre.antall()); // Utskrift: 10
        System.out.println(tre.antall(5)); // Utskrift: 0
        System.out.println(tre.antall(4)); // Utskrift: 3
        System.out.println(tre.antall(7)); // Utskrift: 2
        System.out.println(tre.antall(10)); // Utskrift: 1





    }


} // ObligSBinTre
