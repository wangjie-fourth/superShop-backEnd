package com.example.web.controller;

import com.example.domain.dataobject.Location;
import com.example.enums.LocationExistStatusEnum;
import com.example.enums.LocationSelectedStatusEnum;
import com.example.service.LocationService;
import com.example.web.VO.ResultVO;
import com.example.web.VO.location.LocationVO;
import com.example.web.VO.location.LocationsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/21.
 */
@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/add")
    public ResultVO<String> addLocation(
            @RequestParam("name")String name,
            @RequestParam("phone")String phone,
            @RequestParam("address")String address,
            @RequestParam("selected")boolean select,
            @RequestParam("userId")String userId
    ){
        /*
        * 1、改造成Location对象
        * 2、传递给Service层处理
        * */

        // 1.1 处理selected属性
        // 查看数据库表中对应user_id下是否有默认收货地址
        //      如果有，将其修改为非默认状态
        //      如果没有，则什么都不做
        int selected = 0;
        if(select == true){
            // 将新添加的Location 设置为默认收货地址
            selected = LocationSelectedStatusEnum.SELECTED.getCode();
            // 修改表中含有默认状态的收货地址信息
            locationService.updateLocationSelectedByUserId(userId);
        } else {
            // 新添加的Location 不是默认状态
            selected = LocationSelectedStatusEnum.UN_SELECTED.getCode();
        }
        // 1.2 改造成Location对象
        Location la = new Location();
        la.setUserId(userId);
        la.setLocationSelected(selected);
        la.setLocationPhone(phone);
        la.setLocationAddress(address);
        la.setLocationName(name);

        // 2
        locationService.addLocation(la);

        // 返回结果
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData("0");
        return resultVO;
    }

    @GetMapping("/getAll")
    public ResultVO<LocationsVO> getAllLocationByUserId(
            @RequestParam("id")String userId
    ){
        /*
        * 1、获取用户的所有收货地址
        * 2、将其修改为前端所需要的格式
        * 3、向前端发送数据
        * */

        // 1、
        List<Location> locationList = locationService.getAllByUserIdAndLocationExist(userId);

        // 2、
        List<LocationVO> vos = new ArrayList<>();
        for (Location l:locationList) {
            LocationVO vo = new LocationVO();

            vo.setLocationId(l.getLocationId());
            vo.setLocationName(l.getLocationName());
            vo.setLocationPhone(l.getLocationPhone());
            vo.setLocationAddress(l.getLocationAddress());
            // 修改selected属性
            if (l.getLocationSelected() == LocationSelectedStatusEnum.SELECTED.getCode()){
                vo.setLocationSelected(true);
            } else {
                vo.setLocationSelected(false);
            }


            vos.add(vo);
        }

        // 3、
        LocationsVO data = new LocationsVO();
        data.setList(vos);
        ResultVO<LocationsVO> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);

        return  resultVO;
    }

    @GetMapping("/getLocationByLocationId")
    public ResultVO<LocationVO> getByLocationId(
            @RequestParam("locationId")String locationId
    ){
        /*
        * 1、根据 locationId 获取 Location
        * 2、转换成前端所需要的数据
        * 3、发送数据
        * */
        //1、
        Location location = locationService.getLocationByLocationId(locationId);
        // 2、
        LocationVO data = new LocationVO();
        data.setLocationId(locationId);
        data.setLocationAddress(location.getLocationAddress());
        data.setLocationPhone(location.getLocationPhone());
        data.setLocationName(location.getLocationName());
        // 修改selected属性
        if (location.getLocationSelected() == LocationSelectedStatusEnum.SELECTED.getCode()){
            data.setLocationSelected(true);
        } else {
            data.setLocationSelected(false);
        }

        // 3、
        ResultVO<LocationVO> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);

        return resultVO;
    }

    @PostMapping("/update")
    public ResultVO<String> updateLocation(
            @RequestParam("id")String id,
            @RequestParam("name")String name,
            @RequestParam("phone")String phone,
            @RequestParam("address")String address,
            @RequestParam("selected")boolean select,
            @RequestParam("userId")String userId
    ){
        /*
        * 1、拼接成Location对象
        * 2、调用service层方法
        * 3、向前端返回数据
        * */

        // 1.1 处理selected属性
        int selected = 0;
        if(select == true){
            // 将更新的Location 设置为默认收货地址
            selected = LocationSelectedStatusEnum.SELECTED.getCode();
            // 查看数据库表中对应user_id下是否有默认收货地址
            //      如果有，将其修改为非默认状态
            //      如果没有，则什么都不做
            locationService.updateLocationSelectedByUserId(userId);
        } else {
            // 新添加的Location 不是默认状态
            selected = LocationSelectedStatusEnum.UN_SELECTED.getCode();
        }
        // 1.2
        Location location = new Location();
        location.setLocationId(id);
        location.setLocationSelected(selected);
        location.setLocationName(name);
        location.setLocationExist(LocationExistStatusEnum.EXIST.getCode());
        location.setLocationAddress(address);
        location.setLocationPhone(phone);
        location.setUserId(userId);

        //2、
        Location returnLoc = locationService.updateLocation(location);

        //3、
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(null);

        return resultVO;
    }

    @GetMapping("/delete")
    public ResultVO<String> deleteLocationById(
            @RequestParam("locationId")String locationId
    ){
        Location location = locationService.deleteLocationById(locationId);
        //
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(null);

        return resultVO;
    }



    // 查询用户的默认收货地址
    @GetMapping("/getByUserId")
    public ResultVO<LocationVO> getLocationByUserId(
            @RequestParam("userId")String userId
    ){
        // 1、根据 userId 查询其默认的收货地址
        List<Location> locationList = locationService.getLocationSelectesByUserId(userId);

        // 2、判断list大小
        //          为0：表示这个用户没有默认的收货地址
        //          为1：表示这个用户有默认收货地址
        if (locationList.size() == 0){
            // 返回给前端
            ResultVO<LocationVO> resultVO = new ResultVO<>();
            resultVO.setCode(-1);
            resultVO.setMsg("失败");
            return resultVO;

        } else if (locationList.size() == 1){
            // 将Location 转成 LocationVO
            Location location = locationList.get(0);
            LocationVO vo = new LocationVO();
            vo.setLocationId(location.getLocationId());
            vo.setLocationName(location.getLocationName());
            vo.setLocationPhone(location.getLocationPhone());
            vo.setLocationAddress(location.getLocationAddress());
            // 将Location的locationSelected的0、1 转换成 LocationVO的locationSelected的true、flase
            boolean flag = ChangeLocationSelectedToLocationVOLocationSelected(location.getLocationSelected());
            vo.setLocationSelected(flag);

            // 返回给前端
            ResultVO<LocationVO> resultVO = new ResultVO<>();
            resultVO.setCode(0);
            resultVO.setMsg("成功");
            resultVO.setData(vo);
            return resultVO;
        }

        // 不应该执行这个
        return null;
    }

    // 将LocationVO的locationSelected的true、flase 转换成 Location的locationSelected的0、1
    private Integer ChangeLocationVOLocationSelectedToLocationSelected(boolean locationSelected){
        if (locationSelected == true){
            return LocationSelectedStatusEnum.SELECTED.getCode();
        } else if (locationSelected == false){
            return LocationSelectedStatusEnum.UN_SELECTED.getCode();
        }
        // 这样不会返回的
        return 0;
    }

    // 将Location的locationSelected的0、1 转换成 LocationVO的locationSelected的true、flase
    private boolean ChangeLocationSelectedToLocationVOLocationSelected(Integer locationSelected) {
        if (locationSelected == LocationSelectedStatusEnum.SELECTED.getCode()){
            return true;
        } else if (locationSelected == LocationSelectedStatusEnum.UN_SELECTED.getCode()){
            return false;
        }
        // 这样不会返回的
        return true;
    }
}
