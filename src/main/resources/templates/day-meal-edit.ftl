<#import "parts/common.ftl" as c>

<@c.page>

    <div class="navi" >
        <a href="/dishes">Dishes page</a> |
        <a href="/foods">Foods page</a> |
        <a href="/meal-plan">Meal plan page</a> |
    </div>
    <div class="error"> 
        <#if errorMessage??>
            ${errorMessage}
        </#if>
    </div>

    <div class="add">

    <table border="0">

    <form method="post">
  
        <tr>
            <td colspan="5"><b>Day meal edit</b></td>
        </tr>
        <tr>
            <td> Дата:</td>
            <td> ${dayMeal.date} </td>
        </tr>
        
        <tr>
         <td> <select name="breakfastName">
            <option label="${'---'+dayMeal.breakfast.name+'---'}">${dayMeal.breakfast.name}</option>
           <#list dishList as dish>
               <option>
                     ${dish.name}
                </option>
            <#else>
                  No choise
           </#list>
           </select> 
        </td> 
        <td> <select name="dinnerName" >
            <option label= "${'---'+dayMeal.dinner.name+'---'}">${dayMeal.dinner.name}</option>
           <#list dishList as dish>
               <option>
                     ${dish.name}
                </option>
            <#else>
                  No choise
           </#list>
        </select> </td>
        <td> <select name="supperName">
            <option label="${'---'+dayMeal.supper.name+'---'}">${dayMeal.supper.name}</option>
           <#list dishList as dish>
               <option>
                     ${dish.name}
                </option>
            <#else>
                  No choise
           </#list>
        </select></td>
        <td><button type="submit">Добавить</button> </td>
    </form>
   </div>
</@c.page>

