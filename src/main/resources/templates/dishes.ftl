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


