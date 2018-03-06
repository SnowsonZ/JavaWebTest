package controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.FormBean;
import bean.Product;

class SaveProductController implements Controller {
    public static final Log logger = LogFactory.getLog(SaveProductController.class);

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FormBean formBean = new FormBean();
        formBean.setName(request.getParameter("name"));
        formBean.setDescription(request.getParameter("description"));
        formBean.setPrice(request.getParameter("price"));
        Product product = new Product();
        product.setName(formBean.getName());
        product.setDescription(formBean.getDescription());
        try {
            product.setPrice(Float.parseFloat(formBean.getPrice()));
        } catch (NumberFormatException e) {
            logger.error("the price param can not parse float");
        }

        return new ModelAndView("formProductSave", "product", product);
    }
}
