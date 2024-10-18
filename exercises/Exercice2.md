
# Using PMD


Pick a Java project from Github (see the [instructions](../sujet.md) for suggestions). Run PMD on its source code using any ruleset (see the [pmd install instruction](./pmd-help.md)). Describe below an issue found by PMD that you think should be solved (true positive) and include below the changes you would add to the source code. Describe below an issue found by PMD that is not worth solving (false positive). Explain why you would not solve this issue.


## Answer


Nous avons exécuté PMD sur un projet que nous avons effectué en ESIR1 dans le cadre du cours de programmation. 


TRUE POSITIVE ISSUE:

Nous avons retenu quatre lignes similaires du rapport PMD qui sont les suivantes:

16 D:/Documents/Jeu/woolshop/src/entity/Actor.java	127 This switch case may be reached by fallthrough from the previous case
17 D:/Documents/Jeu/woolshop/src/entity/Actor.java	133 This switch case may be reached by fallthrough from the previous case
18 D:/Documents/Jeu/woolshop/src/entity/Actor.java	139 This switch case may be reached by fallthrough from the previous case
19 D:/Documents/Jeu/woolshop/src/entity/Actor.java	145 This switch case may be reached by fallthrough from the previous case

Le code correspondant est le suivant:

public void move(DirEnum dir) {
    	switch(dir) {
        	case up:
            	if (!has_moved) {
                	m_y -= 1;
                	has_moved = true;
                	break;
            	}
        	case down:
            	if (!has_moved) {
                	m_y += 1;
                	has_moved = true;
                	break;
            	}
        	case left:
            	if (!has_moved) {
                	m_x -= 1;
                	has_moved = true;
                	break;
            	}
        	case right:
            	if (!has_moved) {
                	m_x += 1;
                	has_moved = true;
                	break;
            	}
        	case no:
    	}
}

Il suffit ici de sortir les "break" des "if"


FALSE POSITIVE ISSUE:

Nous avons également trouvé un problème 
De plus, le rapport PMD nous renvoie un problème qui nous semble peu important à régler:

14 D:/Documents/Jeu/woolshop/src/entity/Actor.java	91 Useless parentheses

Le code est le suivant:

@Override
public boolean isColliding(Entity entity) {
        return (m_x == entity.getX() && m_y == entity.getY());
}

Malgré le fait que les parenthèses après le "return" ne soient effectivement pas indispensables, le fait d'en mettre ou non ne changera rien au résultat que ce bout de code peut avoir. C'est pourquoi il nous semble peu important de le modifier.