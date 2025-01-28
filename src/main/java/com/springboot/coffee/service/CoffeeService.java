package com.springboot.coffee.service;

import com.springboot.coffee.entity.Coffee;
import com.springboot.coffee.repository.CoffeeRepository;
import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExcepionCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {
    private CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    //    1. post 정보가 들어왔을 때 정보 등록을 위한 코딩
    public Coffee createdCoffee (Coffee coffee) {
//        커피코드를 대문자로 변경
        String coffeeCode = coffee.getCoffeeCode().toUpperCase();

//        커피가 겹치는지 확인. 겹치지 않을 때 pass
        verifiedCoffeeCode(coffeeCode);
//        coffee의 coffeeCode를 바꾸자
        coffee.setCoffeeCode(coffeeCode);
//        커피 저장소에 저장하자.
        return coffeeRepository.save(coffee);
    }

//    2.patch 정보가 들어왔을 때 정보 등록을 위한 코딩
    public Coffee updatedCoffee (Coffee coffee) {
//        존재하는 커피인지 확인.
        Coffee findCoffee = findVerifiedCoffee(coffee.getCoffeeId());
//        각 요소들의 값이 들어왔을 때 들어오지 않았을 때 구분지어 저장하기
//        coffee.getkorName() 이 null 값이라면 Optional.empty() 생성 -> ifPresent 실행 X 기존 값 유지 O
        Optional.ofNullable(coffee.getKorName())
                .ifPresent(korName -> findCoffee.setKorName(korName));
        Optional.ofNullable(coffee.getEngName())
                .ifPresent(engName -> findCoffee.setEngName(engName));
        Optional.ofNullable(coffee.getPrice())
                .ifPresent(price -> findCoffee.setPrice(price));

        return coffeeRepository.save(findCoffee);
    }

//    3-1. 단일 커피 조회하기.
    public Coffee findCoffee (long coffeeId) {
//        존재하는 커피인지 확인하기.
        return findVerifiedCoffee(coffeeId);
    }
//    3-2. 전체 커피 조회하기.  ** 페이지네이션 구현하기.
    public List<Coffee> findCoffees() {
        return coffeeRepository.findAll();
    }

//    4. 커피 삭제하기.
    public void deleteCoffee (long coffeeId) {
//        존재하는 커피인지 확인.
        Coffee coffee = findVerifiedCoffee(coffeeId);
        coffeeRepository.delete(coffee);
    }


//    겹치는 커피코드가 있는가.
    private void verifiedCoffeeCode(String coffeeCode) {
        Optional<Coffee> coffee = coffeeRepository.findByCoffeeCode(coffeeCode);
        if(coffee.isPresent())
            throw new BusinessLogicException(ExcepionCode.COFFEE_CODE_EXIST);
    }

//    존재하는 커피인가
    public Coffee findVerifiedCoffee(long coffeeId) {
        Optional<Coffee> coffee = coffeeRepository.findById(coffeeId);
        Coffee findCoffee =
                coffee.orElseThrow(() ->
                        new BusinessLogicException(ExcepionCode.COFFEE_NOT_FOUND));

        return findCoffee;
    }

}
