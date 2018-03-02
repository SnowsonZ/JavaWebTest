<%--
  Created by IntelliJ IDEA.
  User: snowson
  Date: 18-2-27
  Time: 下午1:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" buffer="10kb"
         pageEncoding="UTF-8" %>

<%@include file="index.jsp" %>
<jsp:forward page="index.jsp">
    <jsp:param name="name" value="snowson"/>
</jsp:forward>
<html>
<head>
    <title>Hello World!!!</title>
</head>
<body>
<%--内置对象--%>
<%request.getRequestURI();%>
<%response.setContentType("UTF-8");%>
<%out.println("<html></html>");%>
<%session.setAttribute("name", "snowson");%>
<%--servletContext--%>
<%application.addFilter("", "");%>
<%--pageContext 设置会话级别的attribute--%>
<%pageContext.setAttribute("pageName", "welcome", PageContext.SESSION_SCOPE);%>
<%--servletConfig--%>
<%config.getInitParameter("");%>
<%--HttpJspPage--%>
<%--<%page%>--%>

<%--EL--%>
<%--JSP页面的
javax.servlet.jsp.PageContext--%>
${pageContext.session.setAttribute("", "")}
${pageContext.out.print()}
<%--上下文参数的值--%>
${initParam}
<%--请求参数，不存在同名参数--%>
${param.get("")}
<%--获取请求参数的多个值--%>
${paramValues.get("")}
<%--header--%>
${header.get("")}
<%--servletContext中所有的属性--%>
${applicationScope.get("")}


<%--脚本元素--%>
<%--表达式--%>
<%=System.currentTimeMillis()%>
<%--声明--%>
<%!
    public boolean stringIsNotNull(String content) {
        if (content == null || content.equals("")) {
            return true;
        }
        return false;
    }

    /*@Override*/
    public void jspInit() {
        System.out.println("jspInit ...");
    }

    /*@Override*/
    public void jspDestroy() {
        System.out.println("jspDestroy ...");
    }
%>
<% out.println("Hello Page \n");%>
This is the Hello World Page!!!
</body>
</html>
