package mirror;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import samples.Car;

public class Mirror {

	private static final String BLANK = "   ";
	private static final String LINE = "------------";
	
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
			System.out.println("\nClass name:\n"+ BLANK + c.getCanonicalName());
			System.out.println("\nClass modifiers:\n"+ BLANK + Modifier.toString(c.getModifiers()));
			System.out.println("");
			
			
			System.out.println("Type Parameters");
			TypeVariable[] typeParameters = c.getTypeParameters();
			
			if(typeParameters.length == 0)
			{
				System.out.println(BLANK + "None");
			}
			else
			{
				for(int tp = 0 ; tp < typeParameters.length ; tp++)
				{
					System.out.println(BLANK + (tp+1) + typeParameters[tp].getName());
				}
			}
			System.out.println("");
			
			
			System.out.println("Implemented Interfaces");
			Class[] interfaces = c.getInterfaces();	
			
			if(interfaces.length == 0)
			{
				System.out.println(BLANK + "None");
			}
			else
			{
				for(int i = 0 ; i < interfaces.length ; i++)
				{
					System.out.println(BLANK + (i+1) + interfaces[i].getCanonicalName());
				}
			}
			System.out.println("");
			
			
			System.out.println("Class Inheritance Tree");
			Class pathClass = c;
			List<Class> inheritancePath = new ArrayList<>();

			while(pathClass.getSuperclass()!=null)
			{
				inheritancePath.add(pathClass);	
				pathClass = pathClass.getSuperclass();
			}
			Collections.reverse(inheritancePath);
			if(!inheritancePath.isEmpty())
			{
				System.out.print(BLANK);
				for(int cn = 0 ; cn < inheritancePath.size() ; cn++)
				{
					System.out.print(inheritancePath.get(cn).getName());
					if(cn < inheritancePath.size()-1)
					{
						System.out.print( " -> " );
					}				
				}
				System.out.println("");
			}
			else
			{
				System.out.println(BLANK + "Not available");
			}
			
			System.out.println("");
			
			
			System.out.println("Declared Classes");
			Class[] declaredClasses = c.getDeclaredClasses();
						
			if(declaredClasses.length == 0)
			{
				System.out.println(BLANK + "None");
			}
			else
			{
				for(int dc = 0 ; dc < declaredClasses.length ; dc++)
				{
					System.out.println(BLANK + (dc+1) + declaredClasses[dc].getCanonicalName());
				}
			}
			
			System.out.println("\nEnclosing Class");
			Class enclosingClass = c.getEnclosingClass();
			if(enclosingClass == null)
			{
				System.out.println(BLANK + "None");
			}
			else
			{
				System.out.println(BLANK + enclosingClass.getCanonicalName());				
			}						
			System.out.println("");
			
			System.out.println("Class fields: ");
			Field[] fields = c.getDeclaredFields();
			
			if(fields.length == 0)
			{
				System.out.println(BLANK + "None");
			}
			else
			{
				int fieldCounter = 1;
				for(int fIndex = 0 ; fIndex < fields.length ; fIndex++)
				{
					Field field = fields[fIndex];					
					reflectField(field,fieldCounter,"");
					fieldCounter++;
				}
			}
			System.out.println("");
			
			
			System.out.println("Fields inherited from superclasses: ");
			int totalInheritedFieldsCounter = 0 ;
			Class superClass = c.getSuperclass();
			if(superClass != null)
			{
				int superClassCounter = 1;
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
								System.out.println(BLANK + superClassCounter + " Inherited from: " + superClass.getName());
								totalInheritedFieldsCounter++;
							}
							Field superClassField = superClassFields[fIndex];	
							if(!Modifier.isPrivate(superClassField.getModifiers()))
							{
								reflectField(superClassField,(fIndex+1),BLANK);
							}
						}
					}	
					
					superClass = superClass.getSuperclass();
					superClassCounter++;
				}
				while(superClass != null);
				if(totalInheritedFieldsCounter == 0)
				{
					System.out.println(BLANK + "None");
				}
			}
			else
			{
				System.out.println(BLANK + "None");
			}
			
			
			System.out.println("\nClass methods: ");
			Method[] declaredMethods = c.getDeclaredMethods();
			
			if(declaredMethods.length == 0)
			{
				System.out.println("None");
			}
			else
			{
				int methodCounter = 1;
				for(int i = 0 ; i < declaredMethods.length ; i++)
				{
					Method m = declaredMethods[i];
					reflectMethod(m,methodCounter,"");					
					methodCounter++;
				}
			}
			
			
			System.out.println("Methods inherited from superclasses: ");
			int totalInheritedMethodsCounter = 0 ;
			superClass = c.getSuperclass();
			if(superClass != null && superClass != Object.class)
			{
				int superClassCounter = 1;
				do
				{											
					Method[] superClassMethods = superClass.getDeclaredMethods();
					
					if(superClassMethods.length > 0)
					{
						int superClassMethodsCounter = 0;
						
						for(int mIndex = 0 ; mIndex < superClassMethods.length ; mIndex++)
						{
							if(mIndex == 0)
							{
								System.out.println(BLANK + superClassCounter + " Inherited from: " + superClass.getName());
								totalInheritedFieldsCounter++;
							}
							
							Method superClassMethod = superClassMethods[mIndex];	
							if(!Modifier.isPrivate(superClassMethod.getModifiers()))
							{
								reflectMethod(superClassMethod,(mIndex+1),BLANK);
							}
							
						}
					}	
					
					superClass = superClass.getSuperclass();
					superClassCounter++;
				}
				while(superClass != null && superClass != Object.class);
				if(totalInheritedFieldsCounter == 0)
				{
					System.out.println(BLANK + "None");
				}
			}
			else
			{
				System.out.println(BLANK + "None");
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

	private static void reflectField(Field field, int fieldCounter, String intent)
	{
		String fieldName = field.getName();
		String fieldType = field.getType().getCanonicalName();
		String fieldModifiers = Modifier.toString(field.getModifiers());
		
		System.out.println(intent + BLANK + fieldCounter + " Name: " + fieldName);				
		System.out.println(intent + BLANK + BLANK + "Type: " + fieldType);
		System.out.println(intent + BLANK + BLANK + "Modifiers: " + fieldModifiers);
		System.out.println("");
	}
	
	private static void reflectMethod(Method m, int methodCounter, String intent)
	{
		String methodName = m.getName();
		String methodReturnType = m.getReturnType().toGenericString();
		String methodModifiers = Modifier.toString(m.getModifiers());
		Class[] methodParameterTypes = m.getParameterTypes();
		Class[] methodExceptionTypes = m.getExceptionTypes();
		
		System.out.println(intent + BLANK + methodCounter + "  Name: " + methodName);
		System.out.println(intent + BLANK + BLANK + "Return Type: " + methodName);
		System.out.println(intent + BLANK + BLANK + "Modifiers: " + methodModifiers);
		System.out.println(intent + BLANK + BLANK + "Parameter Types: ");
		if(methodParameterTypes.length == 0)
		{
			System.out.println(intent + BLANK + BLANK + BLANK + "None");
		}
		else
		{
			String parameterTypesString = "";
			for(int p = 0 ; p < methodParameterTypes.length ; p++)
			{
				if(p!=0)
				{
					parameterTypesString = parameterTypesString + ", ";
				}
				parameterTypesString += methodParameterTypes[p].getCanonicalName();
			}
			System.out.println(intent + BLANK + BLANK + BLANK + parameterTypesString);
		}
		
		System.out.println(intent + BLANK + BLANK + "Exception Types: ");
		if(methodExceptionTypes.length == 0)
		{
			System.out.println(intent + BLANK + BLANK + BLANK + "None");
		}
		else
		{
			String exceptionTypesString = "";
			for(int p = 0 ; p < methodExceptionTypes.length ; p++)
			{
				if(p!=0)
				{
					exceptionTypesString = exceptionTypesString + ", ";
				}
				exceptionTypesString += methodExceptionTypes[p].getCanonicalName();
			}
			System.out.println(intent + BLANK + BLANK + BLANK + exceptionTypesString);
		}
		System.out.println("");
	}
	
}
