<#import "parts/common.ftl" as c>

<@c.page>
<h2>Food "${food.name}"</h2>
<div class="navi" >
    <a href="/dishes">Dishes page</a> |
    <a href="/foods">Foods page</a> |
</div>
<div class="error"> 
    <#if errorMessage??>
        ${errorMessage}
    </#if>
</div>
<div class="add">
    <b>Edit food</b>
    <table>
        <tr>
            <td>Name:</td>
            <td>Species:</td>
        </tr>
        <form method="post">
        <tr>
            <td> <input type="text" name="name" value="${food.name}" placeholder="Наименование продукта" /></td>
            <td> <input type="text" name="species" value="${food.species}" placeholder="Подвид"/></td>
            <td><button type="submit">Добавить</button></td>
        </tr>
        </form> 
    </table>
</div>
</@c.page>


