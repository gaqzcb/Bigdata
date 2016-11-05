#Scala Traits
    = Java Interfaces + Concrete methods + much more

#Difference in Scala trait & Scala class
##Exception 01: definition parameters
    trait could not have and class does
##Exception 02: super calls bound in different ways
    statically bound for classes whereas dynamically for traits

#Usage of trait
    1 enrich thin interface
    2 make stackable modifications

#Interpretation of super
    ppt
    
#Trait/Concrete/Abstract class
##01 Concrete
If the behavior will not be reused, just concrete class
If efficiency is very important, lean towards using a class. Most Java runtimes make a virtual
method invocation of a class member a faster operation than an interface method invocation.
##02 Trait
If will used in multiple & unrelated classes
##03 Abstract
If you want to inherit from it in Java code, use an abstract class.Since traits do not have
a Java analog.
If you plan to distribute it in compiled form, and you expect outside groups to write classes
inheriting from it, you might lean towards using abstract class. The issue is that when a trait
gains or loses a member, any classes that inherit from it must be recompiled, even if they have
not changed.If outside clients will only call into the behavior, instead of inheriting from it,
then using a trait is fine.
##04 No idea?
If you still do not know, afster considering the above, then start by making it as a trait. You can
always change it later, and in general using a trait keeps more options open.

#Conclusion

