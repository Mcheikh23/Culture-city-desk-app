package Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author PC
 */
public class Article {

   
     private int idA;
    private String nomA;
    private float prix;
    private int qte;
     private String img;
     private Catégorie id_c;
     public Article(String nom,float prix,int qte,String img,Catégorie id_c ){
    this.nomA=nom;
    this.prix=prix;
     this.qte=qte;  
     this.img=img;
     this.id_c=id_c;
    }
    public Article (int idA,String nom,float prix,int qte,String img,Catégorie id_c) {
    this.idA = idA;
    this.nomA=nom;
    this.prix=prix;
    this.id_c=id_c;
    this.img=img;
    this.qte=qte;
         }

    public Article() {
    }

    public Article(int id, String nom, float prix, int qte, String img) {
     this.idA = idA;
    this.nomA=nom;
    this.prix=prix;
   
    this.img=img;
    this.qte=qte;
      
    }

   
      public int getIdA() {
        return idA;
    }

    public void setIdA(int id) {
        this.idA = id;
    }
      public String getNomA(){
        return nomA;
    }

    public void setNomA(String nom) {
        this.nomA= nom;
    }
    
        public float getPrix(){
       return prix;
    }

    public void setPrix(float prix) {
        this.prix= prix;
    }
        public Catégorie getIdC(){
       return id_c;
    }

    public void setIdC(Catégorie id) {
        this.id_c= id;
    }
        public String getImg(){
       return img;
    }

    public void setImg(String img) {
        this.img= img;
    }
       public int getQte(){
       return qte;
    }

    public void setQte(int qte) {
        this.qte=qte;
    }
      @Override
    public String toString() {
        return "Article{" + "id=" + idA + ", nom=" + nomA + ", id_cat=" + id_c + ", prix=" + prix + ", image=" + img + ", qantité=" + qte +
                '}';
    }
      @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;
        if (this.idA != other.idA) {
            return false;
        }
        return true;
    }
    
    
}
