<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="product-details page-title">
	<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
		<div class="name">${fn:escapeXml(product.name)}<span class="sku">ID</span><span class="code">${fn:escapeXml(product.code)}</span></div>
	</ycommerce:testId>
	<product:productReviewSummary product="${product}" showLinks="true"/>
</div>
<div class="row">
	<div class="col-xs-10 col-xs-push-1 col-sm-6 col-sm-push-0 col-lg-4">
		<product:productImagePanel galleryImages="${galleryImages}" />
	</div>
	<div class="clearfix hidden-sm hidden-md hidden-lg"></div>
	<div class="col-sm-6 col-lg-8">
		<div class="product-main-info">
			<div class="row">
				<div class="col-lg-6">
					<div class="product-details">
						<product:productPromotionSection product="${product}"/>
						<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
							<product:productPricePanel product="${product}" />
						</ycommerce:testId>
                        <c:set var="carbonFootPrintWeight" value="${product.carbonFootPrintWeight}" />

                         <c:if test="${not empty product.carbonFootPrintWeight}">
                                <h4><b>
                                <img src="https://cdn-icons-png.flaticon.com/512/2862/2862791.png" alt="Kg(s)CO2e"
                                width="50" height="50">
                                 ${product.carbonFootPrintWeight} Kg(s)CO2e </b></h4>


                        <table class="styled-table">
                            <thead>
                                    <tr> <hr> </tr>
                                   <tr> <b>Positive impact of choosing this product as Sustainable in the Cart.</b> </tr>
                                <tr>
                                    <th><b>Offset Credit Investment Category</b></th>
                                    <th><b>(%) carbon offset usage</b></th>
                                    <th><b>Usage of carbon offset in Kg(s)</b></th>
                                    <th><b>Third party verified
                                    <img src="https://www.businessworldtrade.com/public/img/business-world-trust.png" alt="Trust" width="50" height="50">
                                    </b>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="active-row">
                                    <td>Renewable Energy</td>
                                    <td>35 %</td>
                                    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${product.carbonFootPrintWeight * 35 /100}"/>  Kg(s)CO2e</td>
                                    <td>
                                           <u><a href="#">Renewable Energy verification</a></u>
                                     </td>
                                </tr>
                                <tr class="active-row">
                                    <td>Recycle Plastic materials</td>
                                    <td>19 %</td>
                                    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${product.carbonFootPrintWeight * 19 /100}"/>  Kg(s)CO2e</td>
                                    <td>
                                           <u><a href="#">Recycle Plastic materials verification</a></u>
                                     </td>
                                </tr>
                                <tr class="active-row">
                                    <td>Recycle Paper</td>
                                    <td>26 %</td>
                                    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${product.carbonFootPrintWeight * 26 /100}"/>  Kg(s)CO2e</td>
                                    <td>
                                           <u><a href="#">Recycle Paper Verification</a></u>
                                     </td>
                                </tr>
                                <tr class="active-row">
                                    <td>Reduce Water Waste</td>
                                    <td>20 %</td>
                                    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${product.carbonFootPrintWeight * 20 /100}"/>  Kg(s)CO2e</td>
                                       <td>
                                          <u><a href="#">Reduce Water Waste Verification</a></u>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </c:if>
						<div class="description">${ycommerce:sanitizeHTML(product.summary)}</div>
					</div>
				</div>

				<div class="col-sm-12 col-md-9 col-lg-6">
					<cms:pageSlot position="VariantSelector" var="component" element="div" class="page-details-variants-select">
						<cms:component component="${component}" element="div" class="yComponentWrapper page-details-variants-select-component"/>
					</cms:pageSlot>
					<cms:pageSlot position="AddToCart" var="component" element="div" class="page-details-variants-select">
						<cms:component component="${component}" element="div" class="yComponentWrapper page-details-add-to-cart-component"/>
					</cms:pageSlot>
				</div>
			</div>
		</div>

	</div>
</div>
