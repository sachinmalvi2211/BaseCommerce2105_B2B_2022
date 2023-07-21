<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<style>
h2   {color: blue;}
p    {color: green;}
</style>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="account-section-header">
    <div class="row">
        <div class="container-lg col-md-6">
            <spring:theme code="text.account.profile.updatePersonalDetails"/>
        </div>
    </div>
</div>

            <div class="account-section-header">
            <img src="https://t3.ftcdn.net/jpg/00/92/12/52/240_F_92125287_raxpUKZgkeMv79JYU2f5VovbPD6GaHiH.jpg" alt="Rewards"
                                               width="90" height="90"><b>You have received ${rewards} Reward Points !</b>
            </div>


<div class="row">
    <div class="container-lg col-md-6">
        <div class="account-section-content">
            <div class="account-section-form">
                <form:form action="update-profile" method="post" modelAttribute="demoUpdateProfileForm">

                    <formElement:formSelectBoxDefaultEnabled idKey="profile.title" labelKey="profile.title" path="titleCode" mandatory="true" skipBlank="false" skipBlankMessageKey="form.select.none" items="${titleData}" selectCSSClass="form-control"/>
                    <formElement:formInputBox idKey="profile.firstName" labelKey="profile.firstName" path="firstName" inputCSS="text" mandatory="true"/>
                    <formElement:formInputBox idKey="profile.lastName" labelKey="profile.lastName" path="lastName" inputCSS="text" mandatory="true"/>


                    <formElement:formCheckbox idKey="profile.permanentSustainable"
                    							labelKey="profile.permanentSustainable"
                    							path="permanentSustainable" inputCSS="add-address-left-input"
                    							labelCSS="add-address-left-label" mandatory="true" />

                    <div class="greenSection">
                    <div>
                    <h2>Why to go with Sustainable ?</h2>
                    <p>Our B2B Business has always been an eco-friendly. In fact, we say that to us, "every day is Earth Day." Our commitment to sustainability and playfulness is part of our DNA and we hope to inspire others to share in this passion.</p>
                    <p>From our 100% recycled materials to our manufacturing, we're raising awareness about sustainability while delivering unquestionably safe products. We believe that the best way to encourage environmental change is through goods people buy and use every day and in our case that's Power-tools products.</p>
                    <p>We care about your customers – how they Handle Tools, what they use with, and what the future holds. We are constantly exploring and innovating to deliver the best products possible for a playful planet for all.</p>
                    </div>
                    <ul>
                        <li><p>We put carbon emission charges into planting trees.</p></li>
                        <li><p>Create recycling policies.</p></li>
                        <li><p>Reduce energy waste and focusing on investing into SOLAR energy</p></li>
                        <li><p>Implement sustainable shipping.</p></li>
                        <li><p>Reduce Paper printing and consumption</p></li>
                        <li><p>Add products that support sustainability</p></li>
                    </ul>
                    </div>
                      <hr>
                    <div class="row">
                        <div class="col-sm-6 col-sm-push-6">
                            <div class="accountActions">
                                <ycommerce:testId code="personalDetails_savePersonalDetails_button">
                                    <button type="submit" class="btn btn-primary btn-block">
                                        <spring:theme code="text.account.profile.saveUpdates" text="Save Updates"/>
                                    </button>
                                </ycommerce:testId>
                            </div>
                        </div>
                        <div class="col-sm-6 col-sm-pull-6">
                            <div class="accountActions">
                                <ycommerce:testId code="personalDetails_cancelPersonalDetails_button">
                                    <button type="button" class="btn btn-default btn-block backToHome">
                                        <spring:theme code="text.account.profile.cancel" text="Cancel"/>
                                    </button>
                                </ycommerce:testId>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>