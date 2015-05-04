package mirror;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import samples.Car;

public class Mirror {

	private static final String BLANK = "   ";
	
	public static void main(String[] args) 
	{
		try 
		{
			String className = "samples.Car"; 
			System.out.println("*------------------------------------------------------*");
			System.out.println("*--------------- MIRROR - CLASS BROWSER ---------------*");
			System.out.println("*------------------------------------------------------*");
			System.out.println("Reflecting class : " + className);
			Class c = Class.forName(className);
			//Class details
			System.out.println("\nClass name: " + c.getCanonicalName());
			System.out.println("\nClass modifiers: "+ Modifier.toString(c.getModifiers()));
			
			
			
			System.out.println("\nType Paramaeters");
			TypeVariable[] typeParameters = c.getTypeParameters();
			
			if(typeParameters.length == 0)
			{
				System.out.println("None");
			}
			else
			{
				for(int tv = 0 ; tv < typeParameters.length ; tv++)
				{
					System.out.println(typeParameters[tv].getName());
				}
			}
			
			
			
			System.out.println("\nImplemented Interfaces");
			Class[] interfaces = c.getInterfaces();	
			
			if(interfaces.length == 0)
			{
				System.out.println("None");
			}
			else
			{
				for(int i = 0 ; i < interfaces.length ; i++)
				{
					System.out.println(interfaces[i].getCanonicalName());
				}
			}
			
			
			
			System.out.println("\nClass Inheritance Tree");
			Class pathClass = c;
			List<Class> inheritancePath = new ArrayList<>();

			while(pathClass.getSuperclass()!=null)
			{
				inheritancePath.add(pathClass);	
				pathClass = pathClass.getSuperclass();
			}
			Collections.reverse(inheritancePath);
			for(int cn = 0 ; cn < inheritancePath.size() ; cn++)
			{
				System.out.print(inheritancePath.get(cn).getName());
				if(cn < inheritancePath.size()-1)
				{
					System.out.print( " -> " );
				}				
			}
			System.out.println("");
			
			
			System.out.println("\nDeclared Classes");
			Class[] declaredClasses = c.getDeclaredClasses();
						
			if(declaredClasses.length == 0)
			{
				System.out.println("None");
			}
			else
			{
				for(int dc = 0 ; dc < declaredClasses.length ; dc++)
				{
					System.out.println(declaredClasses[dc].getCanonicalName());
				}
			}
			
			System.out.println("\nEnclosed Classes");
			Class[] enclosedClasses = c.getDeclaredClasses();
			for(int ec = 0 ; ec < enclosedClasses.length ; ec++)
			{
				System.out.println(enclosedClasses[ec].getCanonicalName());
			}
			
		
			
			System.out.println("\nClass fields: ");
			Field[] fields = c.getDeclaredFields();
			
			if(fields.length == 0)
			{
				System.out.println("None");
			}
			else
			{
				for(int fIndex = 0 ; fIndex < fields.length ; fIndex++)
				{
					Field field = fields[fIndex];
					
					System.out.println(BLANK + " Name: " + field.getName() + " , Type: " + field.getType().getCanonicalName() +", Modifiers: "+ Modifier.toString(field.getModifiers()));				
				}
			}
			
			System.out.println("Fields inherited from superclasses: ");
			int totalInheritedFieldsCounter = 0 ;
			Class superClass = c.getSuperclass();
			if(superClass != null)
			{
				do
				{											
					Field[] superClassFields = superClass.getDeclaredFields();
					
					if(superClassFields.length > 0)
					{
						int superClassFieldsCounter = 0;
						
						for(int fIndex = 0 ; fIndex < superClassFields.length ; fIndex++)
						{
							if(fIndex == 0)
							{
								System.out.println(BLANK + " Inherited from: " + superClass.getName());
								totalInheritedFieldsCounter++;
							}
							Field superClassField = superClassFields[fIndex];	
							if(!Modifier.isPrivate(superClassField.getModifiers()))
							{
								System.out.println(BLANK + " Name: " + superClassField.getName() + " , Type: " + superClassField.getType().getCanonicalName() +", Modifiers: "+ Modifier.toString(superClassField.getModifiers()));									
							}
						}
					}	
					
					superClass = superClass.getSuperclass();
				}
				while(superClass != null);
				if(totalInheritedFieldsCounter == 0)
				{
					System.out.println(BLANK + " None");
				}
			}
			else
			{
				System.out.println(BLANK + " None");
			}
			
			
			
			
						
			
//			Constructor constr = c.getConstructor(String.class, String.class, int.class);
//			System.out.println("Creating a Mazda 2 car");
//			Car car = (Car)constr.newInstance("Mazda","2",150);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		

	}

}
