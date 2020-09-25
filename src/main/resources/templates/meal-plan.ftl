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

            <select name="breakfastName">
                <option label='---Choose breakfast---'> </option>
               <#list dishList as dish>

                   <#assign ggg=breakfastNameValueBeforeError?? && (dish.name==breakfastNameValueBeforeError)>
                   <option  ${(breakfastNameValueBeforeError?? && dish.name==breakfastNameValueBeforeError)?then('selected','')}>
                        ${dish.name}
                    </option>  
                <#else>
                      No choise           
               </#list>
            </select>

            <select name="dinnerName">
                <option label="---Choose dinner---"></option>
               <#list dishList as dish>
                   <option  ${(dinnerNameValueBeforeError?? && dish.name==dinnerNameValueBeforeError)?then('selected','')}>
                        ${dish.name}
                    </option>  
                <#else>
                      No choise           
               </#list>
            </select>

            <select name="supperName">
                <option label="---Choose supper---"></option>
               <#list dishList as dish>
                   <option  ${(supperNameValueBeforeError?? && dish.name==supperNameValueBeforeError)?then('selected','')}>
                        ${dish.name}
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
    <br/><br/>
</@c.page>

