<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
        <h1>Add New Car</h1>  
       <form:form method="post" action="save">    
        <table >    
         <tr>    
          <td>ID : </td>   
          <td><form:input path="name"  /></td>  
         </tr>    
         <tr>    
          <td>Name :</td>    
          <td><form:input path="salary" /></td>  
         </tr>   
         <tr>    
          <td>Model :</td>    
          <td><form:input path="designation" /></td>  
         </tr>   
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Save" /></td>    
         </tr>    
        </table>    
       </form:form>    