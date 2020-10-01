<#import "parts/common.ftl" as c>

<@c.page>
<h1>Dishes</h1>
<div class="navi" >
    <a href="/meal-plan">Meal plan page</a> |
    <a href="/foods">Foods page</a> |
</div>    
<div class="error"> 
    <#if errorMessage??>
        ${errorMessage}
    </#if>
</div>
<div class="add">
    <b>Add new dish</b>
    <form method="post">
        <input type="text" name="dishName" placeholder="Имя" />
        <button type="submit">Добавить</button>
    </form>
</div>
<div class="dish">
    <b>List of dishes</b>
    <table >
        <tr>
            <th>Наименование Блюда</th>
            <th>Инградиенты</th>
            <th colspan="2">Редактирование</th>
        </tr>
        <#list dishList as dish>
        <tr>
            <td>${dish.name}</td>
            <td>${dish.ingredientInfo}</td>
            <td class="edit"><a href="${'dish-' + dish.id + '-edit'}">Edit</a></td>
            <td class="edit"><a href="${'dish-' + dish.id + '-delete'}">Delete</a></td>
        </tr>
        <#else>
            No dish
        </#list>
    </table>          
</div>
</@c.page>



















<!-- 

<#list dishes as dish>
<div>
    <i>${dish.name}</i>
    <string>${dish.ingredientInfo}</string>
    <string>${dish.servingWeight}</string>
</div>
<#else>
    No dish
</#list>







<#list foods as food>
<div>
    <i>${food.name}</i>
    <string>${food.ingredientInfo}</string>
    <string>${food.servingWeght}</string>
</div>
<#else>
    No food
</#list>




<#list foods1 as food1>
<div>
    <i>${food1.id}</i>
    <string>${food1.name}</string>
    <string>${food1.brand}</string>
    <string>${food1.unitWeight}</string>
    <string>${food1.unitPrice}</string>
    <string>${food1.proteins}</string>
    <string>${food1.fats}</string>
    <string>${food1.ccal}</string>
    <string>${food1.shelfLife}</string>
</div>
<#else>
    No food
</#list> -->