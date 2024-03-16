package proj.been433.cofcapshop.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import proj.been433.cofcapshop.item.Coffee;
import proj.been433.cofcapshop.service.ItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemService itemService;

    @PostMapping("/api/v2/itemList")
    public List<Coffee> itemList(@RequestBody @Validated itemListRequest request) {
        List<Coffee> itemListByKeyword = itemService.findItemsByKeyword(request.keyword);
        return itemListByKeyword;
    }

    @Data
    static class itemListRequest {
        private String keyword;
    }
    @Data
    @AllArgsConstructor
    static class itemListResult {
        private List<Coffee> result;
    }

}
