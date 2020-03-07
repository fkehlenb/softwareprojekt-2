#Exceptions

###Persistence

- Lazy initialization exception = List passed from primefaces is of non changeable length, lazy loading needs to be allowed in persistence.xml => **Anti Pattern**
- Detached object passed to persist => Can only persist newly created objects, if **@GeneratedValue** tag is used, object is not longer in a transient state and is therefore detached
- One solution => Use strings instead of that object
- Enums in datatables are treatet as string, so rowstyleclass has to compare to string




