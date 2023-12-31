package de.hybris.platform.commercefacades.order;


import de.hybris.platform.commercefacades.order.data.AddToCartParams;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.CartRestorationData;
import de.hybris.platform.commercefacades.order.data.CommerceCartMetadata;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commerceservices.enums.CountryType;
import de.hybris.platform.commerceservices.order.CommerceCartMergingException;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartRestorationException;
import de.hybris.platform.commerceservices.order.exceptions.IllegalQuoteStateException;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;

import org.apache.commons.lang.NotImplementedException;

public interface CartFacade {

    /**
     * Method gets cart from session, if any exist.
     *
     * @return the cart data
     */
    CartData getSessionCart();

    /**
     * Method gets cart sorted by recently added entries from session, if param is set to true
     *
     * @param recentlyAddedFirst
     *           boolean value to determine whether to return sorted cart data
     * @return the cart data sorted by recently added entries
     */
    CartData getSessionCartWithEntryOrdering(boolean recentlyAddedFirst);

    /**
     * Check if session has a cart.
     *
     * @return <tt>true</tt> if the session has a cart
     */
    boolean hasSessionCart();

    /**
     * Check if session cart has entries, avoiding full conversion of the cart to CartData. For Performance.
     *
     * @return <tt>true</tt> if the cart has any entries.
     */
    boolean hasEntries();

    /**
     * Method gets smaller version of cart, with data necessary for showing mini cart.
     *
     * @return the mini card data
     */
    CartData getMiniCart();

    /**
     * Method for adding a product to cart.
     *
     * @param code
     *           code of product to add
     * @param quantity
     *           the quantity of the product
     * @return the cart modification data that includes a statusCode and the actual quantity added to the cart
     * @throws CommerceCartModificationException
     *            if the cart cannot be modified
     */
    CartModificationData addToCart(String code, long quantity) throws CommerceCartModificationException;

    /**
     * Method for adding a product to cart.
     *
     * @param code
     *           code of product to add
     * @param storeId
     *           The identifier for the store. If null {@see CartFacade#addToCart(String, long)} is used.
     * @param quantity
     *           the quantity of the product
     * @return the cart modification data that includes a statusCode and the actual quantity added to the cart
     * @throws CommerceCartModificationException
     *            if the cart cannot be modified
     */
    CartModificationData addToCart(String code, long quantity, String storeId) throws CommerceCartModificationException;

    /**
     * Method for adding a product to cart.
     *
     * @param addToCartParams
     *           addToCartParams
     * @return the cart modification data that includes a statusCode and the actual quantity added to the cart
     * @throws CommerceCartModificationException
     *            if the cart cannot be modified
     */
    CartModificationData addToCart(AddToCartParams addToCartParams) throws CommerceCartModificationException;

    /**
     * Method for validating every entry in the cart
     *
     * @return a list of cart modifications that includes a statusCode and the actual quantity that the entry was updated
     *         to
     * @throws CommerceCartModificationException
     *            if the cart cannot be modified
     */
    List<CartModificationData> validateCartData() throws CommerceCartModificationException;

    void updateCartEntrySustainanble(long entryNumber, boolean isSustainanble)
            throws CommerceCartModificationException;

    /**
     * Method for updating the number of products.
     *
     * @param entryNumber
     *           the entry number
     * @param quantity
     *           new value of quantity for product
     * @return the cart modification data that includes a statusCode and the actual quantity that the entry was updated
     *         to
     * @throws CommerceCartModificationException
     *            if the cart cannot be modified
     */
    CartModificationData updateCartEntry(long entryNumber, long quantity) throws CommerceCartModificationException;

    /**
     * Method for updating the number of products.
     *
     * @param entryNumber
     *           The entry number
     * @param storeId
     *           The identifier for the store. Can be null.
     * @return the cart modification data that includes a statusCode and the actual quantity that the entry was updated
     *         to
     * @throws CommerceCartModificationException
     *            if the cart cannot be modified
     */
    CartModificationData updateCartEntry(long entryNumber, String storeId) throws CommerceCartModificationException;

    /**
     * Restores the user's saved cart to the session. For non-anonymous users, the cart restored will be for the current
     * user and site.
     *
     * @param guid
     *           the cart guid to restore for Anonymous user.
     * @return the cart restoration data that includes details of any items that could not be restored in part or in
     *         full.
     */
    CartRestorationData restoreSavedCart(String guid) throws CommerceCartRestorationException;

    /**
     * Get the supported delivery countries. The list is sorted alphabetically.
     *
     * @deprecated since 1808. Please use {@link CheckoutFacade#getCountries(CountryType)} instead.
     *
     * @return list of supported delivery countries.
     */
    @Deprecated(since = "1808", forRemoval = true)
    List<CountryData> getDeliveryCountries();

    /**
     * Estimate taxes on the cart.
     *
     * @param deliveryZipCode
     *           delivery zip code used for calculating external taxes
     * @param countryIsoCode
     *           delivery country used for calculating taxes
     * @return cart data that represents the current cart with taxes estimations added
     */
    CartData estimateExternalTaxes(String deliveryZipCode, String countryIsoCode);


    /**
     * Remove the stale carts of the current user.
     */
    void removeStaleCarts();

    /**
     * Restores the anonymous user's cart to the session and sets its user to current user.
     *
     * @param guid
     *           GUID used to find anonymous user's cart
     * @return the cart restoration data that includes details of any items that could not be restored in part or in
     *         full.
     * @throws CommerceCartRestorationException
     */
    CartRestorationData restoreAnonymousCartAndTakeOwnership(String guid) throws CommerceCartRestorationException;

    /**
     * If existing the current session cart is being detached from this session and removed. Afterwards it's no longer
     * available.
     */
    void removeSessionCart();

    /**
     * Returns all carts for current site and user.
     *
     * @return list of carts
     */
    List<CartData> getCartsForCurrentUser();

    /**
     * Returns most recent cart guid for current site and user excluding the list of carts guid passed.
     *
     * @param excludedCartsGuid
     *           the excluded carts guid
     * @return the cart guid
     */
    default String getMostRecentCartGuidForUser(final Collection<String> excludedCartsGuid)
    {
        throw new NotImplementedException("getMostRecentCartGuidForUser not implemented");
    }

    /**
     * Returns session cart guid if any.
     *
     * @return the session cart guid
     */
    default String getSessionCartGuid()
    {
        throw new NotImplementedException("getSessionCartGuid not implemented");
    }

    /**
     * Merges carts from anonymous to given user's cart, restores user's cart to the session, sets its user to current
     * user.
     *
     * @param fromAnonumousCartGuid
     *           - Anonymous user's cart from merging is done
     * @param toUserCartGuid
     *           - User's cart to merge to
     * @return {@link CartRestorationData} object holding information about the restoration
     * @throws CommerceCartMergingException
     * @throws CommerceCartRestorationException
     */
    CartRestorationData restoreAnonymousCartAndMerge(String fromAnonumousCartGuid, String toUserCartGuid)
            throws CommerceCartMergingException, CommerceCartRestorationException;

    /**
     * Merges user's carts, restores merged cart to the session, sets its user to current user.
     *
     * @param fromUserCartGuid
     *           - User's cart from merging is done
     * @param toUserCartGuid
     *           - User's cart to merge to
     * @return {@link CartRestorationData} object holding information about the restoration
     * @throws CommerceCartRestorationException
     * @throws CommerceCartMergingException
     */
    CartRestorationData restoreCartAndMerge(String fromUserCartGuid, String toUserCartGuid)
            throws CommerceCartRestorationException, CommerceCartMergingException;

    /**
     * Updates the given cart entry.
     *
     * @param cartEntry
     *           the order entry data object identifying the order entry to update and holding the updated attributes.
     * @return the cart modification data that includes a statusCode and the actual quantity that the entry was updated
     */
    CartModificationData updateCartEntry(final OrderEntryData cartEntry) throws CommerceCartModificationException;

    /**
     * Updates cart metadata, such as: name, description, expiration time.
     *
     * @param metadata
     *           a bean holding parameters a client may want to pass to the method, attributes can be added to the bean
     *           via beans.xml in your extensions
     * @throws IllegalArgumentException
     *            if any attributes fail validation
     * @throws IllegalQuoteStateException
     *            if the cart is associated with a quote for which the operation cannot be performed
     */
    void updateCartMetadata(CommerceCartMetadata metadata);

    /**
     * Removes group with all subsequent entries.
     *
     * @param groupNumber
     *           group number
     * @return cart modification data with status code: "invalidGroupNumber" - group not found, "notPermitted" - group
     *         can not be removed, null - OK
     */
    CartModificationData removeEntryGroup(@Nonnull Integer groupNumber) throws CommerceCartModificationException;
}
