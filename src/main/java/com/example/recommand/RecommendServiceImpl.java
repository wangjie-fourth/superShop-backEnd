package com.example.recommand;

import com.example.dao.repository.OrderDetailRespository;
import com.example.dao.repository.OrderMasterRespository;
import com.example.dao.repository.ProductRespository;
import com.example.dao.repository.RecommendRespository;
import com.example.domain.dataobject.OrderDetail;
import com.example.domain.dataobject.OrderMaster;
import com.example.domain.dataobject.Product;
import com.example.domain.dataobject.Recommend;
import com.example.enums.OrderStatusEnum;
import com.example.service.DTO.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wangjie_fourth on 2019/5/16.
 */
@Slf4j
@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private ProductRespository productRespository;

    @Autowired
    private OrderMasterRespository masterRespository;

    @Autowired
    private OrderDetailRespository detailRespository;

    @Autowired
    private RecommendRespository recommendRespository;

    // 商品id数组
    public static String[] productIds;

    // 根据订单打分的商品数组
    public static int[][] allProductList;

    // 商品之间的相似度
    public static double[][] productSimilaritysList;


    // 获取商品id数组数据
    public String[] getProductIds(){
        List<Product> list = productRespository.getAllProductIds();
        RecommendServiceImpl.productIds = new String[list.size()];
        for (int i=0; i<list.size();i++){
            productIds[i] = list.get(i).getProductId();
        }
        return productIds;
    }
    // 根据已完结的订单，构造二维数组
    public int[][] getDatasByFinished(){
        int length = productIds.length;
        allProductList = new int[length][length];
        // 获取完结订单主表
        List<OrderMaster> orderMasterList = masterRespository.findAllByOrderStatus(OrderStatusEnum.FINISHED.getCode());
        for(OrderMaster orderMaster : orderMasterList){
            OrderDTO orderDTO = new OrderDTO();
            List<OrderDetail> orderDetailList = detailRespository.findAllByOrderId(orderMaster.getOrderId());
            // 遍历一个订单的商品数据
            for (int i=0; i<orderDetailList.size() ;i++){
                // 订单中的一个商品
                String productId = orderDetailList.get(i).getProductId();
                // 获取该商品在数组的下标
                Integer targetIndex = this.getIndexOfProductId(productId, productIds);
                // 再次遍历订单，为这个商品的推荐打分
                for (int j=0; j<orderDetailList.size() ;j++){
                    // 排除自己为自己打分
                    if (i != j){
                        // 订单中其他商品
                        String otherProductId = orderDetailList.get(j).getProductId();
                        // 得到这个商品在数组的下标
                        Integer otherIndex = this.getIndexOfProductId(otherProductId, productIds);
                        // 为目标商品，打分
                        allProductList[targetIndex][otherIndex] += 1;
                    }
                }
            }
        }
        return allProductList;
    }

    // 根据productId，判断它在数组的下标
    public Integer getIndexOfProductId(String productId,String[] productIds){

        for (int i=0;i<productIds.length;i++){
            if (productId.equals(productIds[i])){
                return i;
            }
        }
        return -1;
    }
    // 计算出商品之间的相似度
    public double[][] calcAllProductSimilaritys(){
        // 计算商品之间的相似度
        productSimilaritysList = new double[allProductList[0].length][allProductList[0].length];
        for (int i=0;i<allProductList.length;i++){
            for (int j=0;j<allProductList.length;j++){
                productSimilaritysList[i][j] = calcTwoProductSimilarity(allProductList[i],allProductList[j]);
            }
        }
        return productSimilaritysList;
    }
    /**
     * 根据订单数据，计算两个商品之间的相似度
     */
    private double calcTwoProductSimilarity(int[] product1,int[] product2){
        float sum=0;
        for(int i=0;i<product1.length;i++){
            sum+=Math.pow(product1[i]-product2[i],2);
        }
        return Math.sqrt(sum);
    }
    // 根据计算的相似度，来重写推荐表数据
    @Override
    public void setRecommendDatas(){
        // 获取商品id数组数据
        getProductIds();
        // 根据订单打分的商品数组
        getDatasByFinished();
        // 计算商品相似度
        calcAllProductSimilaritys();
        // 获取每个商品相似度最高的三个商品
        String[][] recommend = new String[productSimilaritysList[0].length][3];
        // 遍历获取每一行数组中最大三个数的下标
        Integer[][] index = new Integer[productIds.length][3];//存储下标
        for (int i=0; i<productSimilaritysList.length ;i++){
            double[] demo = Arrays.copyOf(productSimilaritysList[i], productSimilaritysList[i].length);
            Arrays.sort(demo);
            double max1 = demo[demo.length-1];
            double max2 = demo[demo.length-2];
            double max3 = demo[demo.length-3];
            // 暂存下标
            for (int j=0;j<productSimilaritysList[i].length;j++){
                if (productSimilaritysList[i][j] == max1){
                    index[i][0] = j;
                } else if (productSimilaritysList[i][j] == max2){
                    index[i][1] = j;
                } else if (productSimilaritysList[i][j] == max3){
                    index[i][2] = j;
                }
            }
        }
        // 写入recommend表中
        for (int i=0; i<index.length;i++){
            String productId1 = productIds[index[i][0]];
            String productId2 = productIds[index[i][1]];
            String productId3 = productIds[index[i][2]];
            Product product = productRespository.findByProductId(productIds[i]);
            Recommend demo = new Recommend();
            demo.setRecommendId(product.getRecommend_id());
            demo.setProductId1(productId1);
            demo.setProductId2(productId2);
            demo.setProductId3(productId3);
            //
            recommendRespository.save(demo);
        }
    }


}
