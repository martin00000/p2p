<#macro loadDictionary sn>
	<#assign itemList =_GUtils._SDUtil.loadItems(sn)/>
	<#list itemList as item>
		<option value="${item.id}">${item.title}</option>
	</#list>
</#macro>
