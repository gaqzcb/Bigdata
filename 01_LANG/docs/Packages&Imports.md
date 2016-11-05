#Scala's flexible imports
    Scala's import clauses are quite a bit more flexible than Java's. There are three
    principal differences. In Scala, imports:
    1、may appear anywhere
    2、may refer to objects(singletn or regular) in addition to packages
    3、let you rename and hide some of the imported members
#Implicit imports
    1、import java.lang._
    2、scala._
    3、Predef._
    The three import clauses above are treated a bit specially in that later imports overshadow earlier ones. For instance, the StringBilder
    class is defied both in package scala and, from Java version 1.5 on, also in package java.lang.
#Access modifiers(*****)
    Note: Members of packages, classes, or objects can be labeled with the access modifiers private and protected. These modifiers restrict
    accesses to the members to certain regions of code. Scala's treatment of access modifiers roughly follows Java's but there are some
    important differences which are explained in this section.
##Private members(*****)
    Scala:::
    class Outer {
      class Inner {
        private def f() { println("f") }
        class InnerMost {
          f()
        }
      }
    //  new Inner().f()   // error: f is not accessible
    }
    Java:::
    public class TestAccessModifier {
        class Outer {
            class Inner {
                private void f() {
                    System.out.println("f");
                }
                class InnerMost {
                    private void fi() {
                        f();
                    }
                }
            }
            private void fo() {
                new Inner().f();
            }
        }
    
        private void foo() {
            Outer outer = new Outer();
            Outer.Inner inner = outer.new Inner();
            inner.f();
        }
    }
    
    Conclusion: given nested classes, visibility scope of private members are different in Scala and Java. 
    In Scala, private member can only be accessed by other members of the class who defines private member,
    whereas, in Java, outer class with any depth has access.
##Protected members(*****)
    Access to protected members is also a bit more restrictive than in Java. In Scala, a
    protected member is only accessible from subclasses of the class in which the member
    is defined. In Java such accesses are also possible from oter classes in the same package.
##Public members(**)
   Every member not labled private or protected is public, they can access anywhere.
    

