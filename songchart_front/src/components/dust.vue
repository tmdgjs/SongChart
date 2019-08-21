<template>
    <div>
<div id="search_input">
            <input id="search_song" placeholder="검색어를 입력하세요."/>
            <button id="search_Btn">
                →
            </button>
        </div>

        <div id="song_info">
            <div id="search_song_category">
                <div id="category_title">곡명</div>
                <div id="category_singer">아티스트</div>
                <div id="category_album">앨범</div>
            </div>
            <Searchitems />
        </div>
    </div>
</template>

<script>
    export default {
        
    }
</script>

<style lang="scss" scoped>

</style>