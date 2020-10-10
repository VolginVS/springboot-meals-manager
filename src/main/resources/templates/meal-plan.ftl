<#import "parts/common.ftl" as c>

<@c.page>
<h1>Meals</h1>
<div class="navi" >
    <a href="/dishes">Dish page</a> |
    <a href="/foods">Food page</a> |
</div>    
<div class="error"> 
    <#if errorMessage??>
        ${errorMessage}
    </#if>
</div>
<div class="add-day-meal">
    <b>Add new day</b>
    <form method="post">
        <input type="date" name="stringDate" placeholder="Date" min="01-01-2020" required>
        <select name="breakfastDishId">
            <option label='---Choose breakfast---'> </option>
            <#list dishList as dish>
            <option label='${dish.name}'  ${(breakfastDishIdValueBeforeError?? && dish.id==breakfastDishIdValueBeforeError)?then('selected','')}>
                ${dish.id}
            </option>  
            <#else>
                  No choise           
            </#list>
        </select>
        <select name="dinnerDishId">
            <option label="---Choose dinner---"></option>
           <#list dishList as dish>
           <option label='${dish.name}' ${(dinnerDishIdValueBeforeError?? && dish.id==dinnerDishIdValueBeforeError)?then('selected','')}>
                ${dish.id}
            </option>  
            <#else>
                No choise           
            </#list>
        </select>
        <select name="supperDishId">
            <option label="---Choose supper---"></option>
            <#list dishList as dish>
            <option label='${dish.name}' ${(supperDishIdValueBeforeError?? && dish.id==supperDishIdValueBeforeError)?then('selected','')}>
                ${dish.id}
            </option>             
            <#else>
                  No choise
            </#list>
        </select>
        <button type="submit">Add</button>
    </form>
</div>
<div class="meal">
    <table border="0">
        <tr>
            <th colspan="4" class="header"><h3>Meal plan</h3></th> 
        </tr>  
        <#list dayMealList as dayMeal>
        <tr>
            <th colspan="4" class="header"> ${dayMeal.date}</th> 
        </tr>  
            <th>Breakfast:</th>
            <td>${dayMeal.breakfast}</td>
            <td class="edit"><a href="${'day-meal-'+dayMeal.date}"> edit</a></td>
            <td class="edit"><a href="${'meal-plan-'+dayMeal.id+'-delete'}">delete</a></td>
        </tr>
        <tr>
            <th>Dinner: </th>
            <td>${dayMeal.dinner}</td>
        </tr>
        <tr>
            <th>Supper:</th>
            <td>${dayMeal.supper}</td>
        </tr>
        <#else>
            Meal plan is empty 
        </#list>
    </table>          
</div>
</@c.page>

