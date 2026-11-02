Feature: Merchant Coupon Creation

  @merchant @coupon @positive
  Scenario Outline: Create coupon with valid details
    When user opens Coupons and Promos
    And user clicks Create Coupon
    And user enters coupon details "<CouponCode>" "<Discount>"
    And user submits the coupon for approval
    And created coupon should be displayed in the table with "Pending" status

    Examples:
      | CouponCode | Discount |
      | SAVE13     | 10       |
      | FESTIVE23  | 20       |

  @merchant @coupon @negative
  Scenario Outline: Validate coupon with invalid details
    When user opens Coupons and Promos
    And user clicks Create Coupon
    And user enters coupon details "<CouponCode>" "<Discount>"
    And user submits the coupon for approval
    Then coupon should not be submitted for approval for "<TestCase>"

    Examples:
      | TestCase               | CouponCode | Discount |
      | BlankCouponCode        |            | 10       |
      | InvalidCouponCode      | @@@%%%     | 10       |
      | ZeroCouponDiscount     | SAVEZERO   | 0        |
      | CouponDiscountAbove100 | SAVEHIGH   | 110      |
      | NegativeCouponDiscount | SAVENEG    | -10      |
