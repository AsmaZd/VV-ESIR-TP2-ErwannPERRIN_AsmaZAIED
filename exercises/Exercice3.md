# Extending PMD

Use XPath to define a new rule for PMD to prevent complex code. The rule should detect the use of three or more nested `if` statements in Java programs so it can detect patterns like the following:

```Java
if (...) {
    ...
    if (...) {
        ...
        if (...) {
            ....
        }
    }

}
```
Notice that the nested `if`s may not be direct children of the outer `if`s. They may be written, for example, inside a `for` loop or any other statement.
Write below the XML definition of your rule.

You can find more information on extending PMD in the following link: https://pmd.github.io/latest/pmd_userdocs_extending_writing_rules_intro.html, as well as help for using `pmd-designer` [here](./designer-help.md).

Use your rule with different projects and describe you findings below. See the [instructions](../sujet.md) for suggestions on the projects to use.

## Answer

Afin de tester notre règle nous avons ecrit le bout de programme suivant:

public class Main{

    public static void main(String[] args){

        int bill;

        if (True) {
            if (True) {
                if (True) {
                    System.out.println("Hello toi");
                }
            }

        }

        if (True) {
            if (True) 
                for (int i = 0; i<2; i++){

                    if (True) {
                        System.out.println("Hello toi");
                    }
                }
            }

        if (True){

            if (True){
                break;
            }
        }
    }
}

En écrivant la règle "//IfStatement//Block[IfStatement//Block[IfStatement//Block]]", les lignes 34-41 sont recoonu ainsi que les lignes 44-51. Cela comprend plus de choses que ce qui nous interesse. On souhaiterais seulement detecter les lignes 36-38 et 47-49, c'est à dire les troisièmes "if" imbriqués.
Avec la règle "//IfStatement//IfStatement//IfStatement" on obtient bien ce que l'on souhaite.

USAGE DE NOTRE REGLE SUR UN PROJET:

