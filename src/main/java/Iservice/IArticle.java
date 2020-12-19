/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.Article;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IArticle {
       public void creerArticle(Article a);
    public void modifierArticle(Article a);
    public void supperimerArticle(Article a);
    public List <Article> afficherArticle( );
    
}
