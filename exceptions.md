#Exceptions

###Persistence

- Lazy initialization exception = List passed from primefaces is of non changeable length, lazy loading needs to be allowed in persistence.xml => **Anti Pattern**
- Detached object passed to persist => Can onl persist newly created objects, if **@GeneratedValue** tag is used, object is not longer in a transient state and is therefore detached




