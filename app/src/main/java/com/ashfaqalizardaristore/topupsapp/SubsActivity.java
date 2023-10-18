package com.ashfaqalizardaristore.topupsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.ashfaqalizardaristore.topupsapp.databinding.ActivitySubsBinding;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubsActivity extends AppCompatActivity {
    private BillingClient billingClient;
    private String subsName, phases, des, dur;
    boolean isSuccess = false;
    private ActivitySubsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        billingClient = BillingClient.newBuilder(this)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();

        binding.btnGetPrice.setOnClickListener(view -> {
            getPrice();
        });

        binding.btnSubscribe.setOnClickListener(view -> {
            subscribeNow();
        });
        getPrice();
    }

    private void subscribeNow() {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                QueryProductDetailsParams queryProductDetailsParams =
                        QueryProductDetailsParams.newBuilder()
                                .setProductList(
                                        ImmutableList.of(
                                                QueryProductDetailsParams.Product.newBuilder()
                                                        .setProductId("1usd")
                                                        .setProductType(BillingClient.ProductType.SUBS)
                                                        .build()))
                                .build();
                billingClient.queryProductDetailsAsync(
                        queryProductDetailsParams,
                        (billingResult1, productDetailsList) -> {
                            for(ProductDetails productDetails: productDetailsList){
                                String selectedOfferToken = "";
                            }
                        }
                );

            }
        });
    }

    private void getPrice() {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.execute(() -> {
                        QueryProductDetailsParams queryProductDetailsParams =
                                QueryProductDetailsParams.newBuilder()
                                        .setProductList(
                                                ImmutableList.of(
                                                        QueryProductDetailsParams.Product.newBuilder()
                                                                .setProductId("1usd")
                                                                .setProductType(BillingClient.ProductType.SUBS)
                                                                .build()))
                                        .build();

                        billingClient.queryProductDetailsAsync(
                                queryProductDetailsParams,
                                new ProductDetailsResponseListener() {
                                    public void onProductDetailsResponse(BillingResult billingResult,
                                                                         List<ProductDetails> productDetailsList) {
                                        // check billingResult
                                        // process returned productDetailsList
                                        for (ProductDetails productDetails : productDetailsList) {
                                            ProductDetails.SubscriptionOfferDetails detail = productDetails.getSubscriptionOfferDetails().get(0);
                                            ProductDetails.PricingPhase price = detail.getPricingPhases().getPricingPhaseList().get(0);
                                            String selectedOfferToken = detail.getOfferToken();

                                            ImmutableList productDetailsParamsList =
                                                    ImmutableList.of(
                                                            BillingFlowParams.ProductDetailsParams.newBuilder()
                                                                    // retrieve a value for "productDetails" by calling queryProductDetailsAsync()
                                                                    .setProductDetails(productDetails)
                                                                    // to get an offer token, call ProductDetails.getSubscriptionOfferDetails()
                                                                    // for a list of offers that are available to the user
                                                                    .setOfferToken(selectedOfferToken)
                                                                    .build()
                                                    );

                                            subsName = productDetails.getName();
                                            des = productDetails.getDescription();
                                            String formattedPrice = price.getFormattedPrice();
                                            String billingPeriod = price.getBillingPeriod();
                                            int recurrenceMode = price.getRecurrenceMode();

                                            String number, duration, mBillingPeriod;
                                            mBillingPeriod = billingPeriod;
                                            number = billingPeriod.substring(1, 2);
                                            duration = billingPeriod.substring(2, 3);
                                            if (recurrenceMode == 2) {
                                                if (duration.equals("M")) {
                                                    dur = " For " + number + " Month";
                                                } else if (duration.equals("Y")) {
                                                    dur = " For " + number + " Year";
                                                } else if (duration.equals("W")) {
                                                    dur = " For " + number + " Week";
                                                } else if (duration.equals("D")) {
                                                    dur = " For " + number + " Day";
                                                }
                                            } else {
                                                if (mBillingPeriod.equals("P1M")) {
                                                    dur = "/Monthly";
                                                } else if (mBillingPeriod.equals("P6M")) {
                                                    dur = "/Every 6 Month";
                                                } else if (mBillingPeriod.equals("P1Y")) {
                                                    dur = "/Yearly";
                                                } else if (mBillingPeriod.equals("P1W")) {
                                                    dur = "/Weekly";
                                                } else if (mBillingPeriod.equals("P3W")) {
                                                    dur = "Every /3 Week";
                                                }
                                            }

                                            phases = formattedPrice + " " + dur;
                                            for (int i = 0; i <= (detail.getPricingPhases().getPricingPhaseList().size()); i++) {
                                                if (i > 0) {
                                                    String period = detail.getPricingPhases().getPricingPhaseList().get(i).getBillingPeriod();
                                                    String mPrice = detail.getPricingPhases().getPricingPhaseList().get(i).getFormattedPrice();

                                                    if (mBillingPeriod.equals("P1M")) {
                                                        dur = "/Monthly";
                                                    } else if (mBillingPeriod.equals("P6M")) {
                                                        dur = "/Every 6 Month";
                                                    } else if (mBillingPeriod.equals("P1Y")) {
                                                        dur = "/Yearly";
                                                    } else if (mBillingPeriod.equals("P1W")) {
                                                        dur = "/Weekly";
                                                    } else if (mBillingPeriod.equals("P3W")) {
                                                        dur = "Every /3 Week";
                                                    }

                                                    phases += "\n" + mPrice + dur;
                                                }
                                            }
                                        }
                                    }
                                }
                        );
                    });
                    runOnUiThread(() -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        binding.txtSubsName.setText(subsName);
                        binding.txtPrice.setText(String.format(getResources().getString(R.string.price), phases));
                        binding.txtBenifits.setText(des);
                    });
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        });
    }

    private PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
        @Override
        public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchases) {
            // To be implemented in a later section.
        }
    };
}