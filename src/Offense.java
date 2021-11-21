import java.util.Random;

import static java.lang.Math.max;

/**
 * class Offense
 * 
 *                      the implementation of an inividual attack started by a hero/monster againist a monster/hero
 * 
 *  Variables:
 * 
 *              Player player:
 * 
 *              HeroObject hero:
 * 
 *              MonsterObject monster:
 * 
 *              int HeroAgility, HeroDexterity, HeroStrength:
 * 
 *              Cell cell:
 * 
 *  Constructor:
 * 
 *              Offense(Player p, MonsterObject mo, Cell c)
 * 
 *  Methods:
 * 
 *              void heroAttacks():
 * 
 *              void monsterAttacks():
 */
public class Offense {
    private Player player;
    private HeroObject hero;
    private MonsterObject monster;
    private int HeroAgility, HeroDexterity, HeroStrength;
    private Cell cell;
    public Offense(Player p, MonsterObject mo, Cell c){
        player = p;
        hero = p.getFirstHeroObject();
        monster = mo;
        cell = c;
        HeroAgility = hero.getAgility();
        HeroDexterity = hero.getDexterity();
        HeroStrength = hero.getStrength();
        if(c.isBush()) HeroDexterity *= 1.1;
        if(c.isCave()) HeroAgility *= 1.1;
        if(c.isKoulou()) HeroStrength *= 1.1;
    }

    public void heroAttacks(){
        boolean dodged = Utils.getDodged(hero.getDodgePossiblity());
        if(dodged){
            System.out.println("MISS! The monster dodged your attack!");
            return;
        }
        int damage = (int) ((HeroStrength + (hero.getCurEquippedWeapon() != null ? hero.getCurEquippedWeapon().getDamage() : 0))*0.05)-monster.getDamageReduction();
        damage = max(damage, 1);
        monster.takeHit(damage);
        System.out.println("Monster " + monster.getName() + " take damage: " + damage + ", remaining HP: " + monster.getHP());
    }

    public void monsterAttacks(){
        boolean dodged = Utils.getDodged(HeroAgility*0.002);
        if(dodged){
            System.out.println("MISS! Hero" +player.getId()+" dodged the attack!");
            return;
        }
        int damage = monster.getDamage() - hero.getDamageReduction();
        damage = max(damage, 1);
        hero.takeHit(damage);
        System.out.println("Hero " + player.getId() + " take damage: " + damage + ", remaining HP: " + hero.getHp());
    }
}
