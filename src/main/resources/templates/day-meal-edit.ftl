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
    <form method="post">
    <table border="0">
        <tr>
            <td colspan="5"><b>Day meal edit</b></td>
        </tr>
        <tr>
            <td> Дата:</td>
            <td> ${dayMeal.date} </td>
        </tr>
        <tr>
            <td> 
            <select name="breakfastDishId">
                <option label="${'---'+dayMeal.breakfast.name+'---'}">${dayMeal.breakfast.id}</option>
                <#list dishList as dish>
                <option label="${dish.name}">
                    ${dish.id}
                </option>
                <#else>
                    No choise
                </#list>
            </select> 
            </td> 
            <td> 
            <select name="dinnerDishId" >
                <option label= "${'---'+dayMeal.dinner.name+'---'}">${dayMeal.dinner.id}</option>
                <#list dishList as dish>
                <option label="${dish.name}">
                    ${dish.id}
                </option>
                <#else>
                      No choise
                </#list>
            </select> 
            </td>
            <td> 
            <select name="supperDishId">
                <option label="${'---'+dayMeal.supper.name+'---'}">${dayMeal.supper.id}</option>
                <#list dishList as dish>
                <option label="${dish.name}">
                     ${dish.id}
                </option>
                <#else>
                      No choise
                </#list>
            </select>
            </td>
            <td>
                <button type="submit">Добавить</button>
            </td>
        </tr>
    </table>
    </form>
</div>
</@c.page>

